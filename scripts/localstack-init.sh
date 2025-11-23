#!/bin/bash
# LocalStack AWS Services Initialization Script
# This script initializes AWS services in LocalStack

set -e

echo "Starting LocalStack initialization..."

# Wait for LocalStack to be ready
echo "Waiting for LocalStack to be ready..."
sleep 10

# Set AWS CLI configuration
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=us-east-1

# LocalStack endpoint
LOCALSTACK_URL="http://localhost:4566"

# Create S3 Bucket
echo "Creating S3 bucket..."
aws --endpoint-url=$LOCALSTACK_URL s3 mb s3://springboot-boilerplate-bucket || echo "S3 bucket already exists"

# Create SQS Queue
echo "Creating SQS queue..."
aws --endpoint-url=$LOCALSTACK_URL sqs create-queue --queue-name springboot-queue || echo "SQS queue already exists"

# Create SNS Topic
echo "Creating SNS topic..."
aws --endpoint-url=$LOCALSTACK_URL sns create-topic --name springboot-topic || echo "SNS topic already exists"

# Create DynamoDB Table
echo "Creating DynamoDB table..."
aws --endpoint-url=$LOCALSTACK_URL dynamodb create-table \
  --table-name springboot-cache \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST || echo "DynamoDB table already exists"

# Create Secrets Manager secret
echo "Creating Secrets Manager secret..."
aws --endpoint-url=$LOCALSTACK_URL secretsmanager create-secret \
  --name springboot/db/password \
  --secret-string "{\"username\":\"postgres\",\"password\":\"postgres\"}" || echo "Secret already exists"

echo "LocalStack initialization completed!"

