import zio.Console.printLine
import zio.ZLayer
import zio.test.{Spec, ZIOSpec, assertTrue}

object CommonTests extends ZIOSpec[Unit] {
  override def spec: Spec[Any, Throwable] = suite("CommonTests")(
    test("Sample test is true") {
      val prg = for {
        _ <- printLine("Sample test")
      } yield {
        assertTrue(true)
      }
      prg
    }
  )

  override def bootstrap: ZLayer[Any, Throwable, Unit] = ZLayer.succeed()
}