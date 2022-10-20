package com.encompasscorporation.data.database

object S3Client {
  def gunzipS3Files(bucket: String, location: String): ZIO[S3, Throwable, ZStream[S3, Exception, String]] =
    for {
      chunk <- zio.s3
        .listObjects(bucket, ListObjectOptions.from(location, 100))
        .map(_.objectSummaries)
      lines <- ZIO {
        chunk
          .map(file => {
            zio.s3.getObject(file.bucketName, file.key)
          })
          .map(_
            .transduce(gunzip(64 * 1024))
            .transduce(
              ZTransducer.utf8Decode >>> ZTransducer.splitLines
            )
          )
          .fold(ZStream.empty)(_ ++ _)
      }
    } yield(lines)
}
