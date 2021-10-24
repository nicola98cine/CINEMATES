/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package apiRest_DynamoDB.model;

import apiRest_DynamoDB.model.InputPayload;

public class Input {
    @com.google.gson.annotations.SerializedName("operation")
    private String operation = null;
    @com.google.gson.annotations.SerializedName("TableName")
    private String tableName = null;
    @com.google.gson.annotations.SerializedName("payload")
    private InputPayload payload = null;

    /**
     * Gets operation
     *
     * @return operation
     **/
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of operation.
     *
     * @param operation the new value
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Gets tableName
     *
     * @return tableName
     **/
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of tableName.
     *
     * @param tableName the new value
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets payload
     *
     * @return payload
     **/
    public InputPayload getPayload() {
        return payload;
    }

    /**
     * Sets the value of payload.
     *
     * @param payload the new value
     */
    public void setPayload(InputPayload payload) {
        this.payload = payload;
    }

}
