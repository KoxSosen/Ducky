FROM debian:buster

MAINTAINER Simon <email.todo>

RUN apt-get install -y wget apt-transport-https gnupg && \
    wget https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public && \
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --import public && \
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --export --output adoptopenjdk-archive-keyring.gpg && \
    rm adoptopenjdk-keyring.gpg && \
    mv adoptopenjdk-archive-keyring.gpg /usr/share/keyrings && chown root:root /usr/share/keyrings/adoptopenjdk-archive-keyring.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/adoptopenjdk-archive-keyring.gpg] https://adoptopenjdk.jfrog.io/adoptopenjdk/deb buster main" | tee /etc/apt/sources.list.d/adoptopenjdk.list && \
    apt-get update && \
    apt-get install adoptopenjdk-11-hotspot && \
    apt-get autoremove --purge -y && \
    rm -rv /var/lib/apt/lists/* && \
    useradd --no-log-init --shell /bin/false --no-create-home ducky-runner

ADD Ducky-*.tar /opt/
RUN chown -R ducky-runner:ducky-runner /opt/Ducky-*.tar && \
    tar -xfz Ducky-*.tar > Ducky

USER ducky-runner
WORKDIR /opt/Ducky/


ENTRYPOINT ["/bin/Ducky"]


