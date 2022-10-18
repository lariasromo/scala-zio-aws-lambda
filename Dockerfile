FROM public.ecr.aws/g8w2v4q4/mozilla/sbt as builder
COPY . /src/
WORKDIR /src/
RUN sbt assembly

FROM public.ecr.aws/lambda/java:11
COPY --from=builder /src/target/scala-2.12/zio-lambda-assembly-*.jar ${LAMBDA_TASK_ROOT}/lib/
CMD ["app.kvell.logStreams.Ingest::handleRequest"]