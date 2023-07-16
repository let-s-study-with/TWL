1. 프로젝트 설정:
    - IntelliJ에서 Java 프로젝트를 열고, 필요한 설정을 완료. 프로젝트를 Maven, Gradle 등의 빌드 도구로 관리하는 경우 해당 도구의 설정 파일(**`pom.xml`** 또는 **`build.gradle`**)을 확인하고 업데이트.
2. 소스 코드 저장소 설정:
    - CI/CD 파이프라인을 위해 소스 코드를 저장소에 연결. GCP에서는 Google Cloud Source Repositories, GitHub, GitLab 등 다양한 소스 코드 저장소를 지원한다. 선택한 저장소에 프로젝트를 생성하고 코드를 푸시.
3. CI/CD 도구 선택:
    - GCP에서는 다양한 CI/CD 도구를 제공. Cloud Build, Jenkins, GitLab CI/CD, CircleCI 등을 활용할 수 있습니다.
4. Cloud Build 설정:
    - GCP 콘솔에 로그인하고, Cloud Build 서비스를 활성화.
    - Cloud Build 설정 파일인 **`cloudbuild.yaml`**을 프로젝트 루트 디렉토리에 생성합니다. 이 파일은 빌드 및 배포 단계를 정의.
    - **`cloudbuild.yaml`** 파일을 편집하여 빌드 단계에서 필요한 작업을 정의. 예를 들어, Maven 또는 Gradle을 사용하여 프로젝트를 빌드하는 단계를 추가할 수 있다.
    - 필요에 따라 배포 단계도 **`cloudbuild.yaml`**에 추가할 수 있다. 예를 들어, GCP의 App Engine, Compute Engine, Kubernetes Engine 등에 배포하는 단계를 추가할 수 있다.
5. CI/CD 파이프라인 트리거 설정:
    - 특정 이벤트가 발생할 때 CI/CD 파이프라인을 자동으로 실행하도록 트리거를 설정할 수 있다. 예를 들어, 코드 푸시, 브랜치 머지 등을 트리거로 사용할 수 있다. 트리거 설정은 저장소에 따라 다를 수 있다.
6. CI/CD 파이프라인 실행:
    - 모든 설정이 완료되었다면, 변경 사항을 커밋하고 저장소로 푸시한다. 이로 인해 CI/CD 파이프라인이 자동으로 실행되고 빌드, 배포 단계가 수행된다. 실행 결과 및 로그는 CI/CD 도구의 대시보드 또는 콘솔에서 확인할 수 있다.
