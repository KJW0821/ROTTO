# 베이스 이미지로 OpenJDK 17 사용
FROM openjdk:17-jdk

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 Spring Boot 애플리케이션 JAR 파일을 복사하여 이미지 내에 추가
COPY build/libs/rotto-0.0.1-SNAPSHOT.jar app.jar

# 포트 8080을 외부에 노출
EXPOSE 8080

# Chrome 및 Chrome WebDriver 설치를 위해 필요한 명령 추가
# Chrome 설치
RUN apt-get update && \
    apt-get install -y wget && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Chrome WebDriver 다운로드 및 설치
ENV WEBDRIVER_PATH /usr/local/bin/chromedriver
ENV WEBDRIVER_VERSION 94.0.4606.61
ENV WEBDRIVER_URL "https://chromedriver.storage.googleapis.com/$WEBDRIVER_VERSION/chromedriver_linux64.zip"

RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget -O /tmp/chromedriver.zip $WEBDRIVER_URL && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin/ && \
    rm /tmp/chromedriver.zip

# 웹 드라이버 실행 파일의 실행 권한 설정
RUN chmod +x $WEBDRIVER_PATH

# Docker 컨테이너 시작 시 실행될 명령 설정
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=production", ">>", "/home/ubuntu/applicationBE.log", "2>&1", "google-chrome-stable", "--headless", "--no-sandbox", "--disable-gpu", "--remote-debugging-address=0.0.0.0", "--remote-debugging-port=9222", "--lang=ko", "--disable-dev-shm-usage"]
