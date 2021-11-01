# Using Debian 10.
FROM debian:buster-slim

# Sets the email.
LABEL maintainer="67807644+KoxSosen@users.noreply.github.com"

# Set version
ENV VERSION 1.6.4

# Install Java 11 (optionally will 16), and nano.
RUN apt-get update && \
    apt-get install -y wget apt-transport-https gnupg && \
    wget https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public && \
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --import public && \
    gpg --no-default-keyring --keyring ./adoptopenjdk-keyring.gpg --export --output adoptopenjdk-archive-keyring.gpg && \
    rm adoptopenjdk-keyring.gpg && \
    mv adoptopenjdk-archive-keyring.gpg /usr/share/keyrings && chown root:root /usr/share/keyrings/adoptopenjdk-archive-keyring.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/adoptopenjdk-archive-keyring.gpg] https://adoptopenjdk.jfrog.io/adoptopenjdk/deb buster main" | tee /etc/apt/sources.list.d/adoptopenjdk.list && \
    apt-get update && \
    apt-get install adoptopenjdk-11-hotspot -y && \
    apt-get install nano -y && \
    apt-get autoremove --purge -y && \
    rm -rv /var/lib/apt/lists/*

# Add the user we're going to run as.
RUN useradd --no-log-init --shell /bin/false --no-create-home ducky-runner

# Add Ducky-<release>.tar from the host working Directory to /opt/ in the container.
COPY Ducky-$VERSION.tar /opt/

# Decompress. Not using ADD because it's not .tar.gz
RUN cd /opt/ && \
    tar -xvf Ducky-$VERSION.tar

# Set ownership.
RUN chown -R ducky-runner:ducky-runner /opt/Ducky-$VERSION/

# Change user, set the working dir.
USER ducky-runner
WORKDIR /opt/Ducky-$VERSION/bin/

# Enter the app. Not using ENTRYPOINT, as we don't use any startup flags rn...Or do we? :eyes:
CMD ["./Ducky"]
