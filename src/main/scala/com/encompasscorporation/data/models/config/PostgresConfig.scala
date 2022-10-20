package com.encompasscorporation.data.models.config

import zio.System.env
import zio.{ULayer, ZIO, ZLayer}

case class PostgresConfig(
                         url: String,
                         username: String,
                         password: String,
                         )

object PostgresConfig {
  def make: ZIO[Any, SecurityException, ULayer[PostgresConfig]] = for {
    url <- env("PG_URL")
    username <- env("PG_USERNAME")
    password <- env("PG_PASSWORD")
  } yield ZLayer.succeed {
    PostgresConfig(
      url=url.getOrElse( throw new Exception("PG_URL should be set") ),
      username=username.getOrElse( throw new Exception("PG_USERNAME should be set") ),
      password=password.getOrElse( throw new Exception("PG_PASSWORD should be set") )
    )
  }
}