# Using Debian 10.
FROM openjdk:18-jdk-alpine3.13

# Sets the email.
LABEL maintainer="67807644+KoxSosen@users.noreply.github.com"

# Create an arg for the version. --build-arg
# At the time of writing this: version=1.6.4
ARG version

# Set version
ENV BUILDVER=$version

# Add the user we're going to run as.
RUN adduser --no-log-init --shell /bin/false --no-create-home ducky-runner

# Add Ducky-<release>.tar from the host working Directory to /opt/ in the container.
COPY Ducky-$BUILDVER.tar /opt/

# Decompress. Not using ADD because it's not .tar.gz
RUN cd /opt/ && \
    tar -xvf Ducky-$BUILDVER.tar

# Set ownership.
RUN chown -R ducky-runner:ducky-runner /opt/Ducky-$BUILDVER/

# Change user, set the working dir.
USER ducky-runner
WORKDIR /opt/Ducky-$BUILDVER/bin/

# Enter the app. Not using ENTRYPOINT, as we don't use any startup flags rn...Or do we? :eyes:
CMD ["./Ducky"]
