import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline
from sklearn.metrics import classification_report
import joblib

from google.colab import drive
drive.mount('/content/drive')

# Load dataset
df = pd.read_json('cft_training_data.json')

# Correct way to parse the data
transactions = df[['description', 'subcategory']].dropna()
data = pd.DataFrame(transactions)

# Split data
X_train, X_test, y_train, y_test = train_test_split(
    data['description'], data['subcategory'], test_size=0.2, random_state=42)

# Create Pipeline
model = Pipeline([
    ('tfidf', TfidfVectorizer(lowercase=True)),  # Always lowercase text!
    ('clf', LogisticRegression(max_iter=1000))
])

# Train
model.fit(X_train, y_train)

# Evaluate
y_pred = model.predict(X_test)
print(classification_report(y_test, y_pred))

# Save model (saving inside Drive to avoid Colab surprises)
joblib.dump(model, '/content/drive/MyDrive/subcategory_classifier.joblib')
