FROM python:3.11-slim-bookworm

WORKDIR /app

COPY app.py /app

CMD ["python3", "app.py"]
