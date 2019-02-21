package org.ionkin.hl7.fhir

import java.nio.charset.StandardCharsets

import com.google.common.net.MediaType
import org.ionkin.hl7.fhir.model.ucum.{Currency, Ucum}

package object model {
  /** Primitive types
    */
  // TODO: all elements should be extends from Element
  // TODO: all values are Option!!!
  type Instant = org.joda.time.Instant
  type Time = org.joda.time.LocalTime
  type Date = org.joda.time.LocalDate
  type DateTime = org.joda.time.DateTime

  object Base64Binary {
    def fromSource(src: String): Base64Binary = new Base64Binary(src.getBytes(StandardCharsets.UTF_16))
  }
  case class Base64Binary(b64: String) {
    def this(srcUtf16: Array[Byte]) = this(new String(java.util.Base64.getEncoder.encode(srcUtf16), StandardCharsets.UTF_16))
    override def toString: String = b64
  }

  type Decimal = BigDecimal // or double with save accurate: 0.10 != 0.1
  // type Boolean = Boolean

  class Code(val str: String) {
    if (str.trim == "") throw new IllegalArgumentException("value should not be empty or whitespace")
  }
  type MarkDown = String // https://github.github.com/gfm/
  case class Id(v: String) {
    if (v.matches("""[A-Za-z0-9\-\.]{1,64}""")) throw new IllegalArgumentException(s"Invalid id: $v")
  }

  type Integer = Int
  case class UnsignedInt(i: Int) {
    if (i < 0) throw new IllegalArgumentException("value must be non negative")
  }
  case class PositiveInt(i: Int) {
    if (i <= 0) throw new IllegalArgumentException("value must be positive")
  }

  class Uri(value: String)
  type Url = java.net.URL
  case class Canonical(url: Url) extends Uri(url.toString)
  type Uuid = java.util.UUID // TODO: to lower case
  class Oid(v: String) extends Uri(v) {
    if (v.matches("""urn:oid:[0-2](\.(0|[1-9][0-9]*))+""")) throw new IllegalArgumentException(s"Invalid Oid: $v")
  }

  /** General-Purpose Data types
   */
  trait Quantity {
   def value: Option[Decimal] // numerical value of the quantity, including an implicit precision
   def comparator: QuantityComparator // If no comparator is specified, the value is a point value (i.e. '=')
   def unit: Option[String] // a displayable unit that defines what is measured
   def system: Option[Uri] // must be present if `code` is present
   def code: Option[Code] // UCUM code if it is provided
  }
  sealed abstract class UcumQuantity(ucum: Ucum) extends Quantity {
    def unit: Option[String] = value.map(v => ucum.unit)
    def system: Option[Uri] = value.map(v => ucum.system)
    def code: Option[Code] = value.map(v => ucum.code)
  }
  sealed abstract class CurrencyQuantity(currency: Currency) extends Quantity {
    def unit: Option[String] = value.map(v => currency.display)
    def system: Option[Uri] = value.map(v => currency.system)
    def code: Option[Code] = value.map(v => currency.code)
  }
  class QuantityComparator(str: String) extends Code(str)
  object QuantityComparator {
    case object Equals extends QuantityComparator("=")
    case object Bigger extends QuantityComparator(">")
    case object BiggerOrEquals extends QuantityComparator(">=")
    case object Smaller extends QuantityComparator("<")
    case object SmallerOrEquals extends QuantityComparator("<=")
  }

  case class Age(value: Option[Decimal], ucum: Ucum = Ucum.TimeUnits.Year, comparator: QuantityComparator = QuantityComparator.Equals) extends UcumQuantity(ucum) {
    value.filter(_ <= 0).foreach(v => throw new IllegalArgumentException("If age's value is present, it SHALL be positive"))
  }
  object Duration {
    import scala.concurrent.duration.{TimeUnit, DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS, MICROSECONDS, NANOSECONDS}
    def toUcum(timeUnit: TimeUnit): Ucum = timeUnit match {
      case DAYS =>  Ucum.TimeUnits.Day
      case HOURS => Ucum.TimeUnits.Hour
      case MINUTES => Ucum.TimeUnits.Minute
      case SECONDS => Ucum.TimeUnits.Second
      case MILLISECONDS => Ucum.TimeUnits.MilliSecond
      case MICROSECONDS => Ucum.TimeUnits.MicroSecond
      case NANOSECONDS => Ucum.TimeUnits.NanoSecond
    }
    def apply(d: scala.concurrent.duration.Duration): Duration = apply(d._1, d._2)
    def apply(time: Long, timeUnit: TimeUnit): Duration = Duration(Some(BigDecimal(time)), toUcum(timeUnit))
  }

  case class Duration(value: Option[Decimal], ucum: Ucum = Ucum.TimeUnits.Day, comparator: QuantityComparator = QuantityComparator.Equals) extends UcumQuantity(ucum)
  case class Distance(value: Option[Decimal], ucum: Ucum = Ucum.SILengthUnits.Meter, comparator: QuantityComparator = QuantityComparator.Equals) extends UcumQuantity(ucum)
  case class Count(value: Option[Decimal], ucum: Ucum = Ucum.Unity.Thousand, comparator: QuantityComparator = QuantityComparator.Equals) extends UcumQuantity(ucum) // TODO: Thousand
  case class Money(value: Option[Decimal], currency: Currency = Currency.RussianRuble, comparator: QuantityComparator = QuantityComparator.Equals) extends CurrencyQuantity(currency)
  case class SimpleQuantity(value: Option[Decimal], unit: Option[String], system: Option[Uri], code: Option[Code]) extends Quantity {
    def comparator: QuantityComparator = QuantityComparator.Equals
  }
  case class Range(low: SimpleQuantity, high: SimpleQuantity) {
    low.value.foreach(l => {high.value.filter(_ <= l).foreach(h => throw new IllegalArgumentException("If present, low SHALL have a lower value than high"))})
    low.code.foreach(lc => high.code.filter(_ != lc).foreach(h => throw new IllegalArgumentException("The code elements of the low or high elements SHALL match")))
    low.unit.foreach(lu => high.unit.filter(_ != lu).foreach(h => throw new IllegalArgumentException("The unit elements of the low or high elements SHALL match")))
    low.system.foreach(ls => high.system.filter(_ != ls).foreach(h => throw new IllegalArgumentException("The system elements of the low or high elements SHALL match")))
  }
  case class Ratio(numerator: Quantity, denominator: Quantity) // TODO: should be Option
  case class Period(start: Option[DateTime], end: Option[DateTime]) {
    start.foreach(s => end.filterNot(_.isAfter(s)).foreach(e => throw new IllegalArgumentException("If present, start SHALL have a lower value than end")))
  }
  class Attachment(mimeType: Option[MediaType], locale: Option[java.util.Locale], data: Option[Base64Binary], uri: Option[Uri], size: Option[UnsignedInt],
                   hash: Option[Base64Binary], title: Option[String], creation: Option[DateTime]) {
    data.foreach(d => if (contentType.isEmpty) throw new IllegalArgumentException("If the Attachment has data, it SHALL have a contentType"))
    def contentType: Option[Code] = mimeType.map(t => new Code(t.`type`))
    def language: Option[Code] = locale.map(l => new Code(l.getLanguage)) // "urn:ietf:bcp:47"
  }
  case class Coding(system: Option[Uri], version: Option[String], code: Option[Code], display: Option[String], userSelected: Option[Boolean])
  case class CodeableConcept(coding: Option[Coding], text: Option[String])

  sealed class Use extends Enumeration {
    case class Use(code: Code, display: Option[String] = None) extends Val(code.str) {
      def displayText: String = display.getOrElse(code.str.capitalize)
    }
    val Usual = Use(new Code("usual"))
    val Official = Use(new Code("official"))
    val Temp = Use(new Code("temp"))
    val Old = Use(new Code("old"))
  }
  object IdentifierUse extends Use {
    val Secondary = Use(new Code("secondary"))

    def system: Uri = new Uri("http://hl7.org/fhir/identifier-use")
    def oid: Oid = new Oid("2.16.840.1.113883.4.642.3.57")
  }
  object HumanNameUse extends Use {
    val Nickname = Use(new Code("nickname"))
    val Anonymous = Use(new Code("anonymous"))
    val Maiden = Use(new Code("maiden"), Some("Name changed for Marriage"))

    def system: Uri = new Uri("http://hl7.org/fhir/name-use")
    def oid: Oid = new Oid("2.16.840.1.113883.4.642.1.66")
  }
  sealed class IdentifierType(code: Code, display: String) {
    private val url = "http://terminology.hl7.org/CodeSystem/v2-0203"
    private val oid = "2.16.840.1.113883.4.642.3.45"
    def system: Url = new Url(url)
    def coding = Coding(Some(new Oid(oid)), Some("4.0.0"), Some(code), Some(display), None)
    def toCodeableConcept: CodeableConcept = CodeableConcept(Some(coding), Some(display))
  }
  object IdentifierType {
    lazy val DriversLicenseNumber = new IdentifierType(new Code("DL"), "Driver's license number")
    lazy val PassportNumber = new IdentifierType(new Code("PPN"), "Passport number")
    lazy val BreedRegistryNumber = new IdentifierType(new Code("BRN"), "Breed Registry Number")
    lazy val MedicalRecordNumber = new IdentifierType(new Code("MR"), "Medical record number")
    lazy val MicrochipNumber = new IdentifierType(new Code("MCN"), "Microchip Number")
    lazy val EmployerNumber = new IdentifierType(new Code("EN"), "Employer number")
    lazy val TaxIdNumber = new IdentifierType(new Code("TAX"), "Tax ID number")
    lazy val NationalInsurancePayorIdentifierPayor = new IdentifierType(new Code("NIIP"), "National Insurance Payor Identifier (Payor)")
    lazy val ProviderNumber = new IdentifierType(new Code("PRN"), "Provider number")
    lazy val MedicalLicenseNumber = new IdentifierType(new Code("MD"), "Medical License number")
    lazy val DonorRegistrationNumber = new IdentifierType(new Code("DR"), "Donor Registration Number")
    lazy val AccessionId = new IdentifierType(new Code("ACSN"), "Accession ID")
    lazy val UniversalDeviceIdentifier = new IdentifierType(new Code("UDI"), "Universal Device Identifier")
    lazy val SerialNumber = new IdentifierType(new Code("SNO"), "Serial Number")
    lazy val SocialBeneficiaryIdentifier = new IdentifierType(new Code("SB"), "Social Beneficiary Identifier")
    lazy val PlacerIdentifier = new IdentifierType(new Code("PLAC"), "Placer Identifier")
    lazy val FillerIdentifier = new IdentifierType(new Code("FILL"), "Filler Identifier")
    lazy val JurisdictionalHealthNumberCanada = new IdentifierType(new Code("JHN"), "Jurisdictional health number (Canada)")
  }
  case class Identifier(use: Option[IdentifierUse.Value], idType: Option[IdentifierType], system: Option[Uri], value: Option[String], period: Option[Period], assigner: Option[Organization]) {
    def `type`: Option[CodeableConcept] = idType.map(_.toCodeableConcept)
    // type should not be used for code - system 1 to 1
  }
  object HumanName {
    def generateName(name: HumanName): Option[String] = {
      val res = List(name.family, Some(name.given.map(g => g.head.toUpper + ".").mkString(" "))).flatten.mkString(", ")
      if (res.isEmpty) None else Some(res)
    }
  }
  case class HumanName(use: Option[HumanNameUse.Value], family: Option[String], given: List[String], prefix: List[String], suffix: List[String], period: Option[Period]) {
    def text: Option[String] = HumanName.generateName(this)
  }

  case class Organization() // TODO
  object Gender {
    object Male extends Code("male")
    object Female extends Code("female")
    object Other extends Code("other")
    object Unknown extends Code("unknown")
  }
  case class Address() // TODO
  object MaritalStatus {  } // TODO
  case class Patient(identifier: List[Identifier], active: Option[Boolean], name: List[HumanName], telecom: List[???],
                     gender: Code = Gender.Unknown, birthDate: Option[Date], ???, address: List[Address], )
}
