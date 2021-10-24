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

import apiRest_DynamoDB.model.InputPayloadItem;
import apiRest_DynamoDB.model.InputPayloadKey;

public class InputPayload {
    @com.google.gson.annotations.SerializedName("Item")
    private InputPayloadItem item = null;
    @com.google.gson.annotations.SerializedName("Key")
    private InputPayloadKey key = null;
    @com.google.gson.annotations.SerializedName("FilterExpression")
    private String filterExpression = null;
    @com.google.gson.annotations.SerializedName("UpdateExpression")
    private String updateExpression = null;
    @com.google.gson.annotations.SerializedName("var")
    private String var = null;
    @com.google.gson.annotations.SerializedName("parameter")
    private String parameter = null;

    /**
     * Gets item
     *
     * @return item
     **/
    public InputPayloadItem getItem() {
        return item;
    }

    /**
     * Sets the value of item.
     *
     * @param item the new value
     */
    public void setItem(InputPayloadItem item) {
        this.item = item;
    }

    /**
     * Gets key
     *
     * @return key
     **/
    public InputPayloadKey getKey() {
        return key;
    }

    /**
     * Sets the value of key.
     *
     * @param key the new value
     */
    public void setKey(InputPayloadKey key) {
        this.key = key;
    }

    /**
     * Gets filterExpression
     *
     * @return filterExpression
     **/
    public String getFilterExpression() {
        return filterExpression;
    }

    /**
     * Sets the value of filterExpression.
     *
     * @param filterExpression the new value
     */
    public void setFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
    }

    /**
     * Gets updateExpression
     *
     * @return updateExpression
     **/
    public String getUpdateExpression() {
        return updateExpression;
    }

    /**
     * Sets the value of updateExpression.
     *
     * @param updateExpression the new value
     */
    public void setUpdateExpression(String updateExpression) {
        this.updateExpression = updateExpression;
    }

    /**
     * Gets var
     *
     * @return var
     **/
    public String getVar() {
        return var;
    }

    /**
     * Sets the value of var.
     *
     * @param var the new value
     */
    public void setVar(String var) {
        this.var = var;
    }

    /**
     * Gets parameter
     *
     * @return parameter
     **/
    public String getParameter() {
        return parameter;
    }

    /**
     * Sets the value of parameter.
     *
     * @param parameter the new value
     */
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

}
