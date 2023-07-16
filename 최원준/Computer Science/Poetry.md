# Poetry

Python의 의존성 관리 및 패키지 관리 시스템.

- 프로젝트 의존성 관리
- 가상 환경 생성
- Python 패키지 빌드 및 배포

과정을 단순화하고 간편화 한다.

- pip install poetry

설치

- poetry init

Poetry 프로젝트 초기화

- poetry add 패키지명

프로젝트에 의존성 추가. 지정한 패키지를 설치하고 프로젝트에 pyproject.toml 파일을 업데이트하여 추가된 의존성 반영. Poetry는 프로젝트의 가상 환경을 자동으로 관리하여 의존성을 시스템의 다른Python 설치와 격리시킨다.

- poetry install

pyproject.toml 파일을 읽고 모든 지정된 의존성을 설치.

- poetry run python my_script.py (예)

가상 환경 내에서 프로젝트의 Python 스크립트를 실행. Poetry는 의존성 해결, 스크립트 실행, 패키지 배포 등 추가적인 기능 제공.

공식문서 : https://python-poetry.org/
