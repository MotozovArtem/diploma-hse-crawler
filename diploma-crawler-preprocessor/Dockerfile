FROM python:3.7

ENV FLASK_RUN_PORT=5001

EXPOSE ${FLASK_RUN_PORT}/tcp

WORKDIR /app

COPY requirements.txt requirements.txt
RUN pip3 install --no-cache-dir -r requirements.txt && python3 -m nltk.downloader punkt && python3 -m nltk.downloader wordnet

COPY . .

CMD FLASK_APP=server.py python3 -m flask run --host=0.0.0.0