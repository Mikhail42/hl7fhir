package org.ionkin.hl7.fhir.model.ucum


import org.ionkin.hl7.fhir.model.{Code, Uri}

import scala.io.Source

object UcumMain extends App {
  val text = Source.fromResource("ucum.csv").getLines
  var lastTheme = ""
  println("object Ucum {")
  text.foreach(str => {
    val Array(c, s, k, cId) = str.split(";")
    if (lastTheme != k) {
      if (lastTheme.nonEmpty) println("  }")
      println(s"  object ${k.replaceAll(" ", "")} {")
      lastTheme = k
    }
    println(s"""    lazy val ${cId} = new Ucum(new Code("${c}"), "${cId}")""")
  })
  println("  }")
  println("}")
}

case class Ucum(code: Code, unit: String) {
  def system: Uri = new Uri("http://download.hl7.de/documents/ucum/ucumdata.html") // TODO: Url
}

object Ucum {
  object MostCommonHealthcareUnits {
    lazy val Percent = new Ucum(new Code("%"), "Percent")
    lazy val PerMicroLiter = new Ucum(new Code("/uL"), "PerMicroLiter")
    lazy val InternationalUnitsPerLiter = new Ucum(new Code("[iU]/L"), "InternationalUnitsPerLiter")
    lazy val ThousandsPerMicroLiter = new Ucum(new Code("10*3/uL"), "ThousandsPerMicroLiter")
    lazy val MillionsPerMicroLiter = new Ucum(new Code("10*6/uL"), "MillionsPerMicroLiter")
    lazy val FemtoLiter = new Ucum(new Code("fL"), "FemtoLiter")
    lazy val GramsPerDeciLiter = new Ucum(new Code("g/dL"), "GramsPerDeciLiter")
    lazy val GramsPerLiter = new Ucum(new Code("g/L"), "GramsPerLiter")
    lazy val GramsPerMilliLiter = new Ucum(new Code("g/mL"), "GramsPerMilliLiter")
    lazy val KiloPascal = new Ucum(new Code("kPa"), "KiloPascal")
    lazy val MilliInternationalUnitsPerMilliLiter = new Ucum(new Code("m[iU]/mL"), "MilliInternationalUnitsPerMilliLiter")
    lazy val MilliEquivalentsPerLiter = new Ucum(new Code("meq/L"), "MilliEquivalentsPerLiter")
    lazy val MilliGramsPerDeciLiter = new Ucum(new Code("mg/dL"), "MilliGramsPerDeciLiter")
    lazy val MilliMetersOfMercury = new Ucum(new Code("mm[Hg]"), "MilliMetersOfMercury")
    lazy val MilliMolesPerKiloGram = new Ucum(new Code("mmol/kg"), "MilliMolesPerKiloGram")
    lazy val MilliMolesPerLiter = new Ucum(new Code("mmol/L"), "MilliMolesPerLiter")
    lazy val MilliOsmolesPerKiloGram = new Ucum(new Code("mosm/kg"), "MilliOsmolesPerKiloGram")
    lazy val NanoGramsPerMilliLiter = new Ucum(new Code("ng/mL"), "NanoGramsPerMilliLiter")
    lazy val NanoMolesPerLiter = new Ucum(new Code("nmol/L"), "NanoMolesPerLiter")
    lazy val PicoGrams = new Ucum(new Code("pg"), "PicoGrams")
    lazy val PicoGramsPerMilliLiter = new Ucum(new Code("pg/mL"), "PicoGramsPerMilliLiter")
    lazy val PicoMolesPerLiter = new Ucum(new Code("pmol/L"), "PicoMolesPerLiter")
    lazy val UnitsPerLiter = new Ucum(new Code("U/L"), "UnitsPerLiter")
    lazy val MicroInternationalUnitsPerMilliLiter = new Ucum(new Code("u[iU]/mL"), "MicroInternationalUnitsPerMilliLiter")
    lazy val MicroGramsPerDeciLiter = new Ucum(new Code("ug/dL"), "MicroGramsPerDeciLiter")
    lazy val MicroGramsPerLiter = new Ucum(new Code("ug/L"), "MicroGramsPerLiter")
    lazy val MicroGramsPerMilliLiter = new Ucum(new Code("ug/mL"), "MicroGramsPerMilliLiter")
    lazy val MicroMolesPerLiter = new Ucum(new Code("umol/L"), "MicroMolesPerLiter")
  }
  object Unity {
    lazy val Log10 = new Ucum(new Code("[lg]"), "Log10")
    lazy val MillionPerSpecimen = new Ucum(new Code("10*6/{Specimen}"), "MillionPerSpecimen")
    lazy val PerTotalCount = new Ucum(new Code("/{tot}"), "PerTotalCount")
    lazy val Thousand = new Ucum(new Code("10*3"), "Thousand")
    lazy val ThousandRedBloodCells = new Ucum(new Code("10*3.{RBC}"), "ThousandRedBloodCells")
    lazy val OneHundredThousand = new Ucum(new Code("10*5"), "OneHundredThousand")
    lazy val Million = new Ucum(new Code("10*6"), "Million")
    lazy val TenToEighth = new Ucum(new Code("10*8"), "TenToEighth")
  }
  object GeneralFractionUnit {
    lazy val Percent = new Ucum(new Code("%"), "Percent")
    lazy val RelativePercent = new Ucum(new Code("{Relative}%"), "RelativePercent")
    lazy val PercentTotal = new Ucum(new Code("%{Total}"), "PercentTotal")
    lazy val Percent0to3Hours = new Ucum(new Code("%{0to3Hours}"), "Percent0to3Hours")
  }
  object NumberFractionUnits {
    lazy val PerTenGiga = new Ucum(new Code("/10*10"), "PerTenGiga")
    lazy val PerMillion = new Ucum(new Code("/10*6"), "PerMillion")
    lazy val PerBillion = new Ucum(new Code("/10*9"), "PerBillion")
    lazy val PerTrillion = new Ucum(new Code("/10*12"), "PerTrillion")
    lazy val PercentNormal = new Ucum(new Code("%{Normal}"), "PercentNormal")
    lazy val PercentSpermMotility = new Ucum(new Code("%{SpermMotility}"), "PercentSpermMotility")
    lazy val PercentPositive = new Ucum(new Code("%{Positive}"), "PercentPositive")
    lazy val PercentFetalErythrocytes = new Ucum(new Code("%{FetalErythrocytes}"), "PercentFetalErythrocytes")
    lazy val PercentOfLymphocytes = new Ucum(new Code("%{OfLymphocytes}"), "PercentOfLymphocytes")
    lazy val PercentofBacteria = new Ucum(new Code("%{ofBacteria}"), "PercentofBacteria")
    lazy val PercentOfWBCs = new Ucum(new Code("%{OfWBCs}"), "PercentOfWBCs")
    lazy val PercentAbnormal = new Ucum(new Code("%{Abnormal}"), "PercentAbnormal")
    lazy val PercentEosinophilsSeen = new Ucum(new Code("%{EosSeen}"), "PercentEosinophilsSeen")
    lazy val PercentHemolysis = new Ucum(new Code("%{Hemolysis}"), "PercentHemolysis")
    lazy val PercentBlockade = new Ucum(new Code("%{Blockade}"), "PercentBlockade")
    lazy val PercentPer100WBC = new Ucum(new Code("%/100{WBC}"), "PercentPer100WBC")
  }
  object MassOrSubstanceFractionUnits {
    lazy val PercentBinding = new Ucum(new Code("%{Binding}"), "PercentBinding")
    lazy val PercentTotalProtein = new Ucum(new Code("%{TotalProtein}"), "PercentTotalProtein")
    lazy val PercentBound = new Ucum(new Code("%{Bound}"), "PercentBound")
    lazy val PercentHemoglobin = new Ucum(new Code("%{Hemoglobin}"), "PercentHemoglobin")
    lazy val PercentHemoglobinSaturation = new Ucum(new Code("%{HemoglobinSaturation}"), "PercentHemoglobinSaturation")
    lazy val PercentCarboxyhemoglobin = new Ucum(new Code("%{Carboxyhemoglobin}"), "PercentCarboxyhemoglobin")
    lazy val PercentHemoglobinA1C = new Ucum(new Code("%{HemoglobinA1C}"), "PercentHemoglobinA1C")
  }
  object MassOrSubstanceRateFractionUnits {
    lazy val PercentExcretion = new Ucum(new Code("%{Excretion}"), "PercentExcretion")
    lazy val PercentUptake = new Ucum(new Code("%{Uptake}"), "PercentUptake")
  }
  object MassRatioOrMassFractionOrMassContentUnits {
    lazy val MicroGramsPerNanoGram = new Ucum(new Code("ug/ng"), "MicroGramsPerNanoGram")
    lazy val NanoGramsPerMilliGram = new Ucum(new Code("ng/mg"), "NanoGramsPerMilliGram")
    lazy val NanoGramsPerMilliGramProtein = new Ucum(new Code("ng/mg{Protein}"), "NanoGramsPerMilliGramProtein")
    lazy val MicroGramsPerMilliGram = new Ucum(new Code("ug/mg"), "MicroGramsPerMilliGram")
    lazy val MicroGramsPerMilliGramCreatinine = new Ucum(new Code("ug/mg{Cre}"), "MicroGramsPerMilliGramCreatinine")
    lazy val MilliGramsPerMilliGram = new Ucum(new Code("mg/mg"), "MilliGramsPerMilliGram")
    lazy val MilligramsPerMilligramCreatinine = new Ucum(new Code("mg/mg{Cre}"), "MilligramsPerMilligramCreatinine")
    lazy val NanoGramsPerGram = new Ucum(new Code("ng/g"), "NanoGramsPerGram")
    lazy val NanoGramsPerGramCreatinine = new Ucum(new Code("ng/g{Cre}"), "NanoGramsPerGramCreatinine")
    lazy val MicroGramsPerGram = new Ucum(new Code("ug/g"), "MicroGramsPerGram")
    lazy val MicroGramPer100Gram = new Ucum(new Code("ug/[100]g"), "MicroGramPer100Gram")
    lazy val MicroGramPerGramDryWeight = new Ucum(new Code("ug/g{DryWeight}"), "MicroGramPerGramDryWeight")
    lazy val MicroGramPerGramCreatinine = new Ucum(new Code("ug/g{Cre}"), "MicroGramPerGramCreatinine")
    lazy val MicroGramsPerGramHemoglobin = new Ucum(new Code("ug/g{Hgb}"), "MicroGramsPerGramHemoglobin")
    lazy val MilliGramsPerGram = new Ucum(new Code("mg/g"), "MilliGramsPerGram")
    lazy val MilliGramPerGramCreatinine = new Ucum(new Code("mg/g{Cre}"), "MilliGramPerGramCreatinine")
    lazy val GramsPerGram = new Ucum(new Code("g/g"), "GramsPerGram")
    lazy val NanoGramsPerKiloGram = new Ucum(new Code("ng/kg"), "NanoGramsPerKiloGram")
    lazy val MicroGramsPerKiloGram = new Ucum(new Code("ug/kg"), "MicroGramsPerKiloGram")
    lazy val MilliGramsPerKiloGram = new Ucum(new Code("mg/kg"), "MilliGramsPerKiloGram")
    lazy val GramsPerKiloGram = new Ucum(new Code("g/kg"), "GramsPerKiloGram")
    lazy val GramsPer100Gram = new Ucum(new Code("g/[100]g"), "GramsPer100Gram")
    lazy val GramsPerGramCreatinine = new Ucum(new Code("g/g{Cre}"), "GramsPerGramCreatinine")
  }
  object SubstanceRatioOrSubstanceFractionUnits {
    lazy val PicoMolesPerMicroMole = new Ucum(new Code("pmol/umol"), "PicoMolesPerMicroMole")
    lazy val NanoMolesPerMilliMole = new Ucum(new Code("nmol/mmol"), "NanoMolesPerMilliMole")
    lazy val NanoMolesPerMilliMoleCreatinine = new Ucum(new Code("nmol/mmol{Cre}"), "NanoMolesPerMilliMoleCreatinine")
    lazy val NanoMolesPerMole = new Ucum(new Code("nmol/mol"), "NanoMolesPerMole")
    lazy val MicroMolesPerMole = new Ucum(new Code("umol/mol"), "MicroMolesPerMole")
    lazy val MilliMolesPerMole = new Ucum(new Code("mmol/mol"), "MilliMolesPerMole")
    lazy val MilliMolesPerMoleCreatinine = new Ucum(new Code("mmol/mol{Cre}"), "MilliMolesPerMoleCreatinine")
    lazy val MicroMolesPerMoleCreatinine = new Ucum(new Code("umol/mol{Cre}"), "MicroMolesPerMoleCreatinine")
    lazy val EquivalentsPerMicroMole = new Ucum(new Code("eq/umol"), "EquivalentsPerMicroMole")
    lazy val EquivalentsPerMilliMole = new Ucum(new Code("eq/mmol"), "EquivalentsPerMilliMole")
    lazy val BoneCollagenEquivalentsPerMilliMoleCreatinine = new Ucum(new Code("{BoneCollagen}eq/mmol{Cre}"), "BoneCollagenEquivalentsPerMilliMoleCreatinine")
    lazy val BoneCollagenEquivalentsPerMicroMoleCreatinine = new Ucum(new Code("{BoneCollagen}eq/umol{Cre}"), "BoneCollagenEquivalentsPerMicroMoleCreatinine")
  }
  object VolumeFractionUnits {
    lazy val VolumePercent = new Ucum(new Code("%{vol}"), "VolumePercent")
    lazy val PercentOxygen = new Ucum(new Code("%{Oxygen}"), "PercentOxygen")
    lazy val MilliLitersPerDeciLiter = new Ucum(new Code("mL/dL"), "MilliLitersPerDeciLiter")
  }
  object CatalyticFractionOrArbitraryFractionUnits {
    lazy val PercentNormalPooledPlasma = new Ucum(new Code("%{NormalPooledPlasma}"), "PercentNormalPooledPlasma")
    lazy val PercentActivity = new Ucum(new Code("%{Activity}"), "PercentActivity")
    lazy val PercentBasalActivity = new Ucum(new Code("%{BasalActivity}"), "PercentBasalActivity")
    lazy val PercentInhibition = new Ucum(new Code("%{Inhibition}"), "PercentInhibition")
  }
  object EntiticNumberUnits {
    lazy val PerEntity = new Ucum(new Code("/{Entity}"), "PerEntity")
    lazy val Per100WBC = new Ucum(new Code("/100{WBC}"), "Per100WBC")
    lazy val Per100 = new Ucum(new Code("/100"), "Per100")
    lazy val Per100Spermatozoa = new Ucum(new Code("/100{Spermatozoa}"), "Per100Spermatozoa")
    lazy val PerTrillionRedBloodCells = new Ucum(new Code("/10*12{rbc}"), "PerTrillionRedBloodCells")
  }
  object PlaneAngleUnits {
    lazy val DegreesOfArc = new Ucum(new Code("deg"), "DegreesOfArc")
  }
  object ArbitraryNumberUnits {
    lazy val PerArbitraryUnit = new Ucum(new Code("/[arb`U]"), "PerArbitraryUnit")
  }
  object ArbitraryUnits {
    lazy val MicroInternationalUnit = new Ucum(new Code("u[iU]"), "MicroInternationalUnit")
    lazy val InternationalUnit = new Ucum(new Code("[iU]"), "InternationalUnit")
    lazy val MillionInternationalUnit = new Ucum(new Code("10*6.[iU]"), "MillionInternationalUnit")
  }
  object EnglishLengthUnits {
    lazy val Inch = new Ucum(new Code("[in_i]"), "Inch")
    lazy val Feet = new Ucum(new Code("[ft_i]"), "Feet")
    lazy val Yard = new Ucum(new Code("[yd_i]"), "Yard")
    lazy val Fathom = new Ucum(new Code("[fth_i]"), "Fathom")
    lazy val StatuteMile = new Ucum(new Code("[mi_i]"), "StatuteMile")
    lazy val NauticalMile = new Ucum(new Code("[nmi_i]"), "NauticalMile")
    lazy val French = new Ucum(new Code("[Ch]"), "French")
  }
  object SILengthUnits {
    lazy val FemtoMeter = new Ucum(new Code("fm"), "FemtoMeter")
    lazy val PicoMeter = new Ucum(new Code("pm"), "PicoMeter")
    lazy val NanoMeter = new Ucum(new Code("nm"), "NanoMeter")
    lazy val MicroMeter = new Ucum(new Code("um"), "MicroMeter")
    lazy val MilliMeter = new Ucum(new Code("mm"), "MilliMeter")
    lazy val CentiMeter = new Ucum(new Code("cm"), "CentiMeter")
    lazy val DeciMeter = new Ucum(new Code("dm"), "DeciMeter")
    lazy val Meter = new Ucum(new Code("m"), "Meter")
    lazy val KiloMeter = new Ucum(new Code("km"), "KiloMeter")
  }
  object EnglishMassUnits {
    lazy val Grain = new Ucum(new Code("[gr]"), "Grain")
    lazy val Ounce = new Ucum(new Code("[oz_av]"), "Ounce")
    lazy val TroyOunce = new Ucum(new Code("[oz_tr]"), "TroyOunce")
    lazy val Pound = new Ucum(new Code("[lb_av]"), "Pound")
    lazy val Ton = new Ucum(new Code("[ston_av]"), "Ton")
    lazy val Dram = new Ucum(new Code("[dr_av]"), "Dram")
  }
  object SIMassUnits {
    lazy val FemtoGram = new Ucum(new Code("fg"), "FemtoGram")
    lazy val PicoGram = new Ucum(new Code("pg"), "PicoGram")
    lazy val NanoGram = new Ucum(new Code("ng"), "NanoGram")
    lazy val MicroGram = new Ucum(new Code("ug"), "MicroGram")
    lazy val MicroGramsPerTotalVolume = new Ucum(new Code("ug/{TotalVolume}"), "MicroGramsPerTotalVolume")
    lazy val MicroGramsPerSpecimen = new Ucum(new Code("ug/{Specimen}"), "MicroGramsPerSpecimen")
    lazy val MilliGram = new Ucum(new Code("mg"), "MilliGram")
    lazy val MilliGramsPerVolume = new Ucum(new Code("mg/{Volume}"), "MilliGramsPerVolume")
    lazy val MilliGramPerTotalVolume = new Ucum(new Code("mg/{TotalVolume}"), "MilliGramPerTotalVolume")
    lazy val Gram = new Ucum(new Code("g"), "Gram")
    lazy val GramsPerTotalWeight = new Ucum(new Code("g/{TotalWeight}"), "GramsPerTotalWeight")
    lazy val DeciGram = new Ucum(new Code("dg"), "DeciGram")
    lazy val CentiGram = new Ucum(new Code("cg"), "CentiGram")
    lazy val KiloGram = new Ucum(new Code("kg"), "KiloGram")
    lazy val MetricTon = new Ucum(new Code("t"), "MetricTon")
  }
  object LineicMassUnits {
    lazy val PicoGramsPerMilliMeter = new Ucum(new Code("pg/mm"), "PicoGramsPerMilliMeter")
    lazy val GramMeterPerHeartbeatPerSquareMeter = new Ucum(new Code("g.m/({hb}.m2)"), "GramMeterPerHeartbeatPerSquareMeter")
  }
  object TemperatureUnits {
    lazy val DegreesKelvin = new Ucum(new Code("K"), "DegreesKelvin")
    lazy val DegreesCelsius = new Ucum(new Code("Cel"), "DegreesCelsius")
    lazy val DegreesFahrenheit = new Ucum(new Code("[degF]"), "DegreesFahrenheit")
  }
  object ThermalResistanceUnits {
    lazy val KelvinPerWatt = new Ucum(new Code("K/W"), "KelvinPerWatt")
  }
  object TimeUnits {
    lazy val PicoSecond = new Ucum(new Code("ps"), "PicoSecond")
    lazy val NanoSecond = new Ucum(new Code("ns"), "NanoSecond")
    lazy val MicroSecond = new Ucum(new Code("us"), "MicroSecond")
    lazy val MilliSecond = new Ucum(new Code("ms"), "MilliSecond")
    lazy val Second = new Ucum(new Code("s"), "Second")
    lazy val KiloSecond = new Ucum(new Code("ks"), "KiloSecond")
    lazy val Megasecond = new Ucum(new Code("Ms"), "Megasecond")
    lazy val Minute = new Ucum(new Code("min"), "Minute")
    lazy val Hour = new Ucum(new Code("h"), "Hour")
    lazy val Day = new Ucum(new Code("d"), "Day")
    lazy val Week = new Ucum(new Code("wk"), "Week")
    lazy val Month = new Ucum(new Code("mo"), "Month")
    lazy val Year = new Ucum(new Code("a"), "Year")
  }
  object SubstanceUnits {
    lazy val Equivalent = new Ucum(new Code("eq"), "Equivalent")
    lazy val MicroEquivalent = new Ucum(new Code("ueq"), "MicroEquivalent")
    lazy val MilliEquivalent = new Ucum(new Code("meq"), "MilliEquivalent")
    lazy val MilliEquivalentsPerSpecimen = new Ucum(new Code("meq/{Specimen}"), "MilliEquivalentsPerSpecimen")
    lazy val Mole = new Ucum(new Code("mol"), "Mole")
    lazy val MilliMole = new Ucum(new Code("mmol"), "MilliMole")
    lazy val MilliMolesPerTotalVolume = new Ucum(new Code("mmol/{TotalVolume}"), "MilliMolesPerTotalVolume")
    lazy val Femtomole = new Ucum(new Code("fmol"), "Femtomole")
    lazy val PicoMole = new Ucum(new Code("pmol"), "PicoMole")
    lazy val MicroMole = new Ucum(new Code("umol"), "MicroMole")
    lazy val NanoMole = new Ucum(new Code("nmol"), "NanoMole")
    lazy val MilliOsmole = new Ucum(new Code("mosm"), "MilliOsmole")
  }
  object AreicSubstanceUnits {
    lazy val MilliEquivalentsPerSquareMeter = new Ucum(new Code("meq/m2"), "MilliEquivalentsPerSquareMeter")
    lazy val MilliMolesPerSquareMeter = new Ucum(new Code("mmol/m2"), "MilliMolesPerSquareMeter")
  }
  object EnglishAreaUnits {
    lazy val SquareInch = new Ucum(new Code("[sin_i]"), "SquareInch")
    lazy val SquareFeet = new Ucum(new Code("[sft_i]"), "SquareFeet")
    lazy val SquareYard = new Ucum(new Code("[syd_i]"), "SquareYard")
  }
  object SIAreaUnits {
    lazy val SquareMilliMeter = new Ucum(new Code("mm2"), "SquareMilliMeter")
    lazy val SquareCentiMeter = new Ucum(new Code("cm2"), "SquareCentiMeter")
    lazy val SquareMeter = new Ucum(new Code("m2"), "SquareMeter")
  }
  object EnglishVolumeUnits {
    lazy val FluidOunce = new Ucum(new Code("[foz_us]"), "FluidOunce")
    lazy val CubicInch = new Ucum(new Code("[cin_i]"), "CubicInch")
    lazy val Cup = new Ucum(new Code("[cup_us]"), "Cup")
    lazy val Pint = new Ucum(new Code("[pt_us]"), "Pint")
    lazy val Quart = new Ucum(new Code("[qt_us]"), "Quart")
    lazy val Gallon = new Ucum(new Code("[gal_us]"), "Gallon")
    lazy val FluidDram = new Ucum(new Code("[fdr_us]"), "FluidDram")
  }
  object SIVolumeUnits {
    lazy val FemtoLiter = new Ucum(new Code("fL"), "FemtoLiter")
    lazy val PicoLiter = new Ucum(new Code("pL"), "PicoLiter")
    lazy val NanoLiter = new Ucum(new Code("nL"), "NanoLiter")
    lazy val MicroLiter = new Ucum(new Code("uL"), "MicroLiter")
    lazy val MilliLiter = new Ucum(new Code("mL"), "MilliLiter")
    lazy val MilliLitersPerHeartbeat = new Ucum(new Code("mL/{h`b}"), "MilliLitersPerHeartbeat")
    lazy val Liter = new Ucum(new Code("L"), "Liter")
    lazy val DeciLiter = new Ucum(new Code("dL"), "DeciLiter")
    lazy val CentiLiter = new Ucum(new Code("cL"), "CentiLiter")
    lazy val KiloLiter = new Ucum(new Code("kL"), "KiloLiter")
    lazy val HectoLiter = new Ucum(new Code("hL"), "HectoLiter")
  }
  object VolumeDurationUnits {
    lazy val LiterSquareSecondPerSecond = new Ucum(new Code("L.s2/s"), "LiterSquareSecondPerSecond")
  }
  object NumberContentUnits {
    lazy val PerMilliGram = new Ucum(new Code("/mg"), "PerMilliGram")
    lazy val PerGram = new Ucum(new Code("/g"), "PerGram")
    lazy val PerGramCreatinine = new Ucum(new Code("/g{creat}"), "PerGramCreatinine")
    lazy val PerGramHemoglobin = new Ucum(new Code("/g{HGB}"), "PerGramHemoglobin")
    lazy val PerGramTotalNitrogen = new Ucum(new Code("/g{tot`nit}"), "PerGramTotalNitrogen")
    lazy val PerGramTotalProtein = new Ucum(new Code("/g{tot`prot}"), "PerGramTotalProtein")
    lazy val PerGramWetTissue = new Ucum(new Code("/g{wet`tis}"), "PerGramWetTissue")
    lazy val PerKiloGram = new Ucum(new Code("/kg"), "PerKiloGram")
    lazy val PerKiloGramBodyWeight = new Ucum(new Code("/kg{body`wt}"), "PerKiloGramBodyWeight")
  }
  object SubstanceContentUnits {
    lazy val FemtoMolesPerMilliGram = new Ucum(new Code("fmol/mg"), "FemtoMolesPerMilliGram")
    lazy val NanoMolesPerMilliGram = new Ucum(new Code("nmol/mg"), "NanoMolesPerMilliGram")
    lazy val MicroMolesPerMilliGram = new Ucum(new Code("umol/mg"), "MicroMolesPerMilliGram")
    lazy val MicroMolesPerMilliGramCreatinine = new Ucum(new Code("umol/mg{Cre}"), "MicroMolesPerMilliGramCreatinine")
    lazy val MolesPerKiloGram = new Ucum(new Code("mol/kg"), "MolesPerKiloGram")
    lazy val FemtoMolesPerGram = new Ucum(new Code("fmol/g"), "FemtoMolesPerGram")
    lazy val NanoMolesPerGram = new Ucum(new Code("nmol/g"), "NanoMolesPerGram")
    lazy val NanoMolesPerGramCreatinine = new Ucum(new Code("nmol/g{Cre}"), "NanoMolesPerGramCreatinine")
    lazy val MicroMolesPerGram = new Ucum(new Code("umol/g"), "MicroMolesPerGram")
    lazy val MicroMolesPerGramCreatinine = new Ucum(new Code("umol/g{Cre}"), "MicroMolesPerGramCreatinine")
    lazy val MicroMolesPerGramHemoglobin = new Ucum(new Code("umol/g{Hgb}"), "MicroMolesPerGramHemoglobin")
    lazy val MilliMolesPerGram = new Ucum(new Code("mmol/g"), "MilliMolesPerGram")
    lazy val MilliMolesPerKiloGram = new Ucum(new Code("mmol/kg"), "MilliMolesPerKiloGram")
    lazy val MilliEquivalentsPerGram = new Ucum(new Code("meq/g"), "MilliEquivalentsPerGram")
    lazy val MilliEquivalentsPerGramCreatinine = new Ucum(new Code("meq/g{Cre}"), "MilliEquivalentsPerGramCreatinine")
    lazy val MilliEquivalentsPerKiloGram = new Ucum(new Code("meq/kg"), "MilliEquivalentsPerKiloGram")
    lazy val OsmolesPerKiloGram = new Ucum(new Code("osm/kg"), "OsmolesPerKiloGram")
  }
  object ArbitraryConcentrationContentUnits {
    lazy val InternationalUnitsPerGram = new Ucum(new Code("[iU]/g"), "InternationalUnitsPerGram")
    lazy val InternationalUnitsPerGramHemoglobin = new Ucum(new Code("[iU]/g{Hgb}"), "InternationalUnitsPerGramHemoglobin")
    lazy val EhrlichUnitsPer100Gram = new Ucum(new Code("{Ehrlich_U}/100g"), "EhrlichUnitsPer100Gram")
    lazy val InternationalUnitsPerKilogram = new Ucum(new Code("[iU]/kg"), "InternationalUnitsPerKilogram")
  }
  object SubstanceRateContentUnits {
    lazy val MicroMolesPerMinutePerGram = new Ucum(new Code("umol/min/g"), "MicroMolesPerMinutePerGram")
    lazy val MilliUnitsPerGram = new Ucum(new Code("mU/g"), "MilliUnitsPerGram")
    lazy val MilliUnitsPerGramHemoglobin = new Ucum(new Code("mU/g{Hgb}"), "MilliUnitsPerGramHemoglobin")
    lazy val UnitsPerGram = new Ucum(new Code("U/g"), "UnitsPerGram")
    lazy val UnitsPerGramHemoglobin = new Ucum(new Code("U/g{Hgb}"), "UnitsPerGramHemoglobin")
    lazy val UnitsPerGramCreatinine = new Ucum(new Code("U/g{Cre}"), "UnitsPerGramCreatinine")
    lazy val MilliUnitsPerMilliGramCreatinine = new Ucum(new Code("mU/mg{Cre}"), "MilliUnitsPerMilliGramCreatinine")
    lazy val MilliUnitsPerMilligram = new Ucum(new Code("mU/mg"), "MilliUnitsPerMilligram")
    lazy val KiloUnitsPerGram = new Ucum(new Code("kU/g"), "KiloUnitsPerGram")
    lazy val KatalPerKilogram = new Ucum(new Code("kat/kg"), "KatalPerKilogram")
    lazy val MilliOsmolesPerKiloGram = new Ucum(new Code("mosm/kg"), "MilliOsmolesPerKiloGram")
  }
  object VolumeContentUnits {
    lazy val MilliLitersPerKiloGram = new Ucum(new Code("mL/kg"), "MilliLitersPerKiloGram")
    lazy val LitersPerKilogram = new Ucum(new Code("L/kg"), "LitersPerKilogram")
  }
  object EnergyContentUnits {
    lazy val KiloCaloriesPerOunce = new Ucum(new Code("kCal/[oz_av]"), "KiloCaloriesPerOunce")
  }
  object AreicNumberUnits {
    lazy val PerSquareMeter = new Ucum(new Code("/m2"), "PerSquareMeter")
  }
  object AreicMassUnits {
    lazy val GramsPerSquareMeter = new Ucum(new Code("g/m2"), "GramsPerSquareMeter")
    lazy val KiloGramsPerSquareMeter = new Ucum(new Code("kg/m2"), "KiloGramsPerSquareMeter")
    lazy val MicroGramsPerSquareMeter = new Ucum(new Code("ug/m2"), "MicroGramsPerSquareMeter")
    lazy val MilliGramsPerSquareMeter = new Ucum(new Code("mg/m2"), "MilliGramsPerSquareMeter")
    lazy val NanoGramsPerSquareMeter = new Ucum(new Code("ng/m2"), "NanoGramsPerSquareMeter")
  }
  object MassiveDistanceUnits {
    lazy val GramMeter = new Ucum(new Code("g.m"), "GramMeter")
    lazy val GramMeterPerHeartbeat = new Ucum(new Code("g.m/{hb}"), "GramMeterPerHeartbeat")
  }
  object MolarMassUnits {
    lazy val KiloGramsPerMole = new Ucum(new Code("kg/mol"), "KiloGramsPerMole")
  }
  object NumberConcentrationUnits {
    lazy val PerMicroLiter = new Ucum(new Code("/uL"), "PerMicroLiter")
    lazy val CellsPerMicroLiter = new Ucum(new Code("{Cells}/uL"), "CellsPerMicroLiter")
    lazy val RedBloodCellsPerMicroLiter = new Ucum(new Code("{rbc}/uL"), "RedBloodCellsPerMicroLiter")
    lazy val ThousandsPerMicroLiter = new Ucum(new Code("10*3/uL"), "ThousandsPerMicroLiter")
    lazy val MillionsPerMicroLiter = new Ucum(new Code("10*6/uL"), "MillionsPerMicroLiter")
    lazy val BillionsPerMicroLiter = new Ucum(new Code("10*9/uL"), "BillionsPerMicroLiter")
    lazy val PerMilliLiter = new Ucum(new Code("/mL"), "PerMilliLiter")
    lazy val SpermatozoaPerMilliLiter = new Ucum(new Code("{Spermatozoa}/mL"), "SpermatozoaPerMilliLiter")
    lazy val CopiesPerMilliLiter = new Ucum(new Code("{Copies}/mL"), "CopiesPerMilliLiter")
    lazy val ThousandPerMilliLiter = new Ucum(new Code("10*3/mL"), "ThousandPerMilliLiter")
    lazy val ThousandCopiesPerMilliLiter = new Ucum(new Code("10*3{Copies}/mL"), "ThousandCopiesPerMilliLiter")
    lazy val MillionPerMilliLiter = new Ucum(new Code("10*6/mL"), "MillionPerMilliLiter")
    lazy val BillionsPerMilliliter = new Ucum(new Code("10*9/mL"), "BillionsPerMilliliter")
    lazy val ColonyFormingUnitsPerMilliLiter = new Ucum(new Code("{cfu}/mL"), "ColonyFormingUnitsPerMilliLiter")
    lazy val PerDeciLiter = new Ucum(new Code("/dL"), "PerDeciLiter")
    lazy val PerLiter = new Ucum(new Code("/L"), "PerLiter")
    lazy val ThousandPerLiter = new Ucum(new Code("10*3/L"), "ThousandPerLiter")
    lazy val MillionPerLiter = new Ucum(new Code("10*6/L"), "MillionPerLiter")
    lazy val TrillionPerLiter = new Ucum(new Code("10*12/L"), "TrillionPerLiter")
    lazy val BillionPerLiter = new Ucum(new Code("10*9/L"), "BillionPerLiter")
  }
  object MassConcentrationUnits {
    lazy val PicoGramsPerMilliLiter = new Ucum(new Code("pg/mL"), "PicoGramsPerMilliLiter")
    lazy val NanoGramsPerMilliLiter = new Ucum(new Code("ng/mL"), "NanoGramsPerMilliLiter")
    lazy val NanoGramsPerMilliLiterRedBloodCells = new Ucum(new Code("ng/mL{rbc}"), "NanoGramsPerMilliLiterRedBloodCells")
    lazy val MicroGramsPerMilliLiter = new Ucum(new Code("ug/mL"), "MicroGramsPerMilliLiter")
    lazy val MilliGramsPerMilliLiter = new Ucum(new Code("mg/mL"), "MilliGramsPerMilliLiter")
    lazy val GramsPerMilliLiter = new Ucum(new Code("g/mL"), "GramsPerMilliLiter")
    lazy val PicoGramsPerDeciLiter = new Ucum(new Code("pg/dL"), "PicoGramsPerDeciLiter")
    lazy val NanoGramsPerDeciLiter = new Ucum(new Code("ng/dL"), "NanoGramsPerDeciLiter")
    lazy val MicroGramsPerDeciLiter = new Ucum(new Code("ug/dL"), "MicroGramsPerDeciLiter")
    lazy val MicroGramsPerDeciLiterRedBloodCells = new Ucum(new Code("ug/dL{rbc}"), "MicroGramsPerDeciLiterRedBloodCells")
    lazy val MilliGramsPerDeciLiter = new Ucum(new Code("mg/dL"), "MilliGramsPerDeciLiter")
    lazy val MilliGramsPhenylketonesPerDeciLiter = new Ucum(new Code("mg{Phenylketones}/dL"), "MilliGramsPhenylketonesPerDeciLiter")
    lazy val GramsPerDeciLiter = new Ucum(new Code("g/dL"), "GramsPerDeciLiter")
    lazy val NanoGramsPerLiter = new Ucum(new Code("ng/L"), "NanoGramsPerLiter")
    lazy val PicoGramsPerLiter = new Ucum(new Code("pg/L"), "PicoGramsPerLiter")
    lazy val MicroGramsPerLiter = new Ucum(new Code("ug/L"), "MicroGramsPerLiter")
    lazy val MilliGramsPerLiter = new Ucum(new Code("mg/L"), "MilliGramsPerLiter")
    lazy val GramsPerLiter = new Ucum(new Code("g/L"), "GramsPerLiter")
    lazy val KiloGramsPerLiter = new Ucum(new Code("kg/L"), "KiloGramsPerLiter")
    lazy val MilliGramsPerCubicMeter = new Ucum(new Code("mg/m3"), "MilliGramsPerCubicMeter")
    lazy val KiloGramsPerCubicMeter = new Ucum(new Code("kg/m3"), "KiloGramsPerCubicMeter")
  }
  object SubstanceConcentrationUnits {
    lazy val FemtoMolesPerMilliLiter = new Ucum(new Code("fmol/mL"), "FemtoMolesPerMilliLiter")
    lazy val PicoMolesPerMilliLiter = new Ucum(new Code("pmol/mL"), "PicoMolesPerMilliLiter")
    lazy val NanoMolesPerMilliLiter = new Ucum(new Code("nmol/mL"), "NanoMolesPerMilliLiter")
    lazy val MicroMolesPerMilliLiter = new Ucum(new Code("umol/mL"), "MicroMolesPerMilliLiter")
    lazy val MolesPerMilliLiter = new Ucum(new Code("mol/mL"), "MolesPerMilliLiter")
    lazy val PicoMolesPerDeciLiter = new Ucum(new Code("pmol/dL"), "PicoMolesPerDeciLiter")
    lazy val NanoMolesPerDeciLiter = new Ucum(new Code("nmol/dL"), "NanoMolesPerDeciLiter")
    lazy val MicroMolesPerDeciLiter = new Ucum(new Code("umol/dL"), "MicroMolesPerDeciLiter")
    lazy val MilliMolesPerDeciLiter = new Ucum(new Code("mmol/dL"), "MilliMolesPerDeciLiter")
    lazy val MilliMolesPerLiter = new Ucum(new Code("mmol/L"), "MilliMolesPerLiter")
    lazy val PicoMolesPerLiter = new Ucum(new Code("pmol/L"), "PicoMolesPerLiter")
    lazy val NanoMolesPerLiter = new Ucum(new Code("nmol/L"), "NanoMolesPerLiter")
    lazy val MicroMolesPerLiter = new Ucum(new Code("umol/L"), "MicroMolesPerLiter")
    lazy val MolesPerLiter = new Ucum(new Code("mol/L"), "MolesPerLiter")
    lazy val MolesPerCubicMeter = new Ucum(new Code("mol/m3"), "MolesPerCubicMeter")
    lazy val MicroEquivalentsPerMilliLiter = new Ucum(new Code("ueq/mL"), "MicroEquivalentsPerMilliLiter")
    lazy val MilliEquivalentPerMilliLiter = new Ucum(new Code("meq/mL"), "MilliEquivalentPerMilliLiter")
    lazy val EquivalentsPerMilliLiter = new Ucum(new Code("eq/mL"), "EquivalentsPerMilliLiter")
    lazy val AHGEquivalentsPerMilliLiter = new Ucum(new Code("{AHG}eq/mL"), "AHGEquivalentsPerMilliLiter")
    lazy val MillionEquivalentsPerMilliLiter = new Ucum(new Code("10*6.eq/mL"), "MillionEquivalentsPerMilliLiter")
    lazy val MicroEquivalentsPerLiter = new Ucum(new Code("ueq/L"), "MicroEquivalentsPerLiter")
    lazy val MilliEquivalentsPerLiter = new Ucum(new Code("meq/L"), "MilliEquivalentsPerLiter")
    lazy val EquivalentsPerLiter = new Ucum(new Code("eq/L"), "EquivalentsPerLiter")
    lazy val MilliEquivalentsPerDeciLiter = new Ucum(new Code("meq/dL"), "MilliEquivalentsPerDeciLiter")
    lazy val MilliOsmolesPerLiter = new Ucum(new Code("mosm/L"), "MilliOsmolesPerLiter")
    lazy val OsmolesPerLiter = new Ucum(new Code("osm/L"), "OsmolesPerLiter")
  }
  object ArbitraryConcentrationUnits {
    lazy val MicroInternationalUnitsPerMilliLiter = new Ucum(new Code("u[iU]/mL"), "MicroInternationalUnitsPerMilliLiter")
    lazy val MilliInternationalUnitsPerMilliLiter = new Ucum(new Code("m[iU]/mL"), "MilliInternationalUnitsPerMilliLiter")
    lazy val IgGPhospholipidUnitsPerMilliLiter = new Ucum(new Code("{IgGPhospholipid}U/mL"), "IgGPhospholipidUnitsPerMilliLiter")
    lazy val IgMPhospholipidUnitsPerMilliLiter = new Ucum(new Code("{IgMPhospholipid}U/mL"), "IgMPhospholipidUnitsPerMilliLiter")
    lazy val ComplementCh50UnitsPerMilliLiter = new Ucum(new Code("{ComplementCh50}U/mL"), "ComplementCh50UnitsPerMilliLiter")
    lazy val IgAPhospholipidUnitsPerMilliLiter = new Ucum(new Code("{IgAPhospholipid}U/mL"), "IgAPhospholipidUnitsPerMilliLiter")
    lazy val ElisaUnitsPerMilliLiter = new Ucum(new Code("{Elisa_U}/mL"), "ElisaUnitsPerMilliLiter")
    lazy val InternationalUnitsPerMilliLiter = new Ucum(new Code("[iU]/mL"), "InternationalUnitsPerMilliLiter")
    lazy val KiloInternationalUnitsPerMilliLiter = new Ucum(new Code("k[iU]/mL"), "KiloInternationalUnitsPerMilliLiter")
    lazy val InternationalUnitsPerDeciLiter = new Ucum(new Code("[iU]/dL"), "InternationalUnitsPerDeciLiter")
    lazy val EhrlichUnitsPerDeciLiter = new Ucum(new Code("{Ehrlich_U}/dL"), "EhrlichUnitsPerDeciLiter")
    lazy val MilliInternationalUnitsPerLiter = new Ucum(new Code("m[iU]/L"), "MilliInternationalUnitsPerLiter")
    lazy val InternationalUnitsPerLiter = new Ucum(new Code("[iU]/L"), "InternationalUnitsPerLiter")
  }
  object pHUnits {
    lazy val pH = new Ucum(new Code("[pH]"), "pH")
  }
}
