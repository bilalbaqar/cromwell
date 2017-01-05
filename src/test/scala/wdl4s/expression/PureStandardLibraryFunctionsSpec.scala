import wdl4s.expression.PureStandardLibraryFunctions
import wdl4s.types.{WdlArrayType, WdlIntegerType}
import wdl4s.values.{WdlArray, WdlInteger}
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Success

class PureStandardLibraryFunctionsSpec extends FlatSpec with Matchers {

  behavior of "transpose"

  it should "transpose a 2x3 into a 3x2" in {
    val inArray = WdlArray(WdlArrayType(WdlArrayType(WdlIntegerType)), List(
      WdlArray(WdlArrayType(WdlIntegerType), List(WdlInteger(1), WdlInteger(2), WdlInteger(3))),
      WdlArray(WdlArrayType(WdlIntegerType), List(WdlInteger(4), WdlInteger(5), WdlInteger(6)))
    ))

    val expectedResult = WdlArray(WdlArrayType(WdlArrayType(WdlIntegerType)), List(
      WdlArray(WdlArrayType(WdlIntegerType), List(WdlInteger(1), WdlInteger(4))),
      WdlArray(WdlArrayType(WdlIntegerType), List(WdlInteger(2), WdlInteger(5))),
      WdlArray(WdlArrayType(WdlIntegerType), List(WdlInteger(3), WdlInteger(6)))
    ))

    PureStandardLibraryFunctions.transpose(Seq(Success(inArray))) should be(Success(expectedResult))
  }

  behavior of "length"

  it should "get the right answers" in {

    val two = WdlArray(WdlArrayType(WdlIntegerType), List(WdlInteger(1), WdlInteger(2)))
    PureStandardLibraryFunctions.length(Seq(Success(two))) should be(Success(WdlInteger(2)))

    val empty = WdlArray(WdlArrayType(WdlIntegerType), List.empty)
    PureStandardLibraryFunctions.length(Seq(Success(empty))) should be(Success(WdlInteger(0)))
  }

}
