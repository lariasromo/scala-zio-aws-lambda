package com.encompasscorporation.data.algebras

import com.encompasscorporation.data.models.config.ProgramConfig
import zio.Console.printLine
import zio.ZIO

object APIv3Export {
  def runExport = for {
    config <- ZIO.service[ProgramConfig]

    _ <- printLine("Running export from " + config.base_url)
  } yield ()

}
