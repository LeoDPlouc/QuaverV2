FROM ubuntu:latest
LABEL authors="moma"

ENTRYPOINT ["top", "-b"]