# Using Alpine, and Java 18
FROM openjdk:18-jdk-alpine3.15 AS multistage

# Sets the email.
LABEL "maintainer"="67807644+KoxSosen@users.noreply.github.com"
LABEL "website"="https://hahota.net/"
LABEL "desc"="Docker image for the Ducky Discord Bot."

# Create an arg for the version. --build-arg=version=1.6.5
ARG version

# Set the env variable to be the arg.
ENV BUILDVER=$version

# Add Ducky-<release>.tar from the host working Directory to /opt/ in the container.
COPY Ducky-$BUILDVER.tar /opt/

# Decompress. Not using ADD because it's not .tar.gz
RUN cd /opt/ && \
    tar -xvf Ducky-$BUILDVER.tar

# Mv so we can keep arg compatibility.
RUN mv /opt/Ducky-$BUILDVER/ /opt/Ducky/

# Make single layered
FROM openjdk:18-jdk-alpine3.15

# Make a dir so we can chown
RUN cd /opt/ && \
    mkdir Ducky

COPY --from=multistage /opt/Ducky/ /opt/Ducky/

# Add the user we're going to run as & set ownership.
RUN adduser -D -H ducky-runner && \
    chown -R ducky-runner:ducky-runner /opt/Ducky/

# Change user, set the working dir.
USER ducky-runner
WORKDIR /opt/Ducky/bin/

# Enter the app.
CMD ["./Ducky"]