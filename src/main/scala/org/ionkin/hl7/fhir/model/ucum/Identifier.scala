package org.ionkin.hl7.fhir.model.ucum

object IdentifierMain extends App {

  case class Model(code: String, display: String) {
    def name: String = display.split(" ").map(_.capitalize).map {
      case "ID" => "Id"
      case x => x
    }.mkString("").replaceAll("[^A-Za-z0-9]", "")

    override def toString: String = s"""    lazy val ${name} = new IdentifierType(new Code("$code"), "$display")"""
  }
  // http://www.hl7.org/fhir/valueset-identifier-type.html
  val lines: Array[String] = """DL	Driver's license number
              |PPN	Passport number
              |BRN	Breed Registry Number
              |MR	Medical record number
              |MCN	Microchip Number
              |EN	Employer number
              |TAX	Tax ID number
              |NIIP	National Insurance Payor Identifier (Payor)
              |PRN	Provider number
              |MD	Medical License number
              |DR	Donor Registration Number
              |ACSN	Accession ID
              |UDI	Universal Device Identifier
              |SNO	Serial Number
              |SB	Social Beneficiary Identifier
              |PLAC	Placer Identifier
              |FILL	Filler Identifier
              |JHN	Jurisdictional health number (Canada)""".stripMargin.split("\n")
  val listOfListOfWords: Array[List[String]] = lines.map(str => str.split("\\s").toList)
  val listOfModels: Array[Model] = listOfListOfWords.map {
    case h :: tail => Model(h, tail.mkString(" "))
  }
  println("  object IdentifierType {")
  listOfModels.foreach(println)
  println("  }")
}
