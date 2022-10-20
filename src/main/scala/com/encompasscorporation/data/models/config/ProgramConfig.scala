package com.encompasscorporation.data.models.config

import zio.System.env
import zio.{ULayer, ZIO, ZLayer}

import scala.util.Try

case class ProgramConfig(
                          base_url: String,
                          profile_limit: Int,
                          entities_limit: Int,
                          source_attributes: List[String],
                        )

object ProgramConfig {
  def make: ZIO[Any, SecurityException, ULayer[ProgramConfig]] = for {
    base_url <- env("BASE_URL")
    profile_limit <- env("PROFILE_LIMIT")
    entities_limit <- env("ENTITIES_LIMIT")
    source_attributes <- env("SOURCE_ATTRIBUTES")
  } yield ZLayer.succeed {
    ProgramConfig(
      base_url=base_url.getOrElse(throw new Exception("BASE_URL should be set")),
      profile_limit=Try(profile_limit.get.toInt)
        .getOrElse(throw new Exception("PROFILE_LIMIT should be set and a number")),
      entities_limit=Try(entities_limit.get.toInt)
        .getOrElse(throw new Exception("ENTITIES_LIMIT should be set and a number")),
      source_attributes=Try(source_attributes.get.split(",").toList)
        .getOrElse(throw new Exception("SOURCE_ATTRIBUTES should be set and should be a comma separated list")),
    )
  }
}
