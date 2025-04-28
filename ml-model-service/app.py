# app.py

from flask import Flask, request, jsonify
import joblib

app = Flask(__name__)

# Load model
model = joblib.load('subcategory_classifier.joblib')

@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    descriptions = data['descriptions']  # List of descriptions
    predictions = model.predict(descriptions)
    return jsonify({'subcategory': predictions.tolist()})

if __name__ == '__main__':
    app.run(port=5000)
