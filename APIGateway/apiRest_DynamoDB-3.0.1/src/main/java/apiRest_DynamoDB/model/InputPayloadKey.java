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


public class InputPayloadKey {
    @com.google.gson.annotations.SerializedName("USERID")
    private String USERID = null;
    @com.google.gson.annotations.SerializedName("IDFILM")
    private String IDFILM = null;

    /**
     * Gets USERID
     *
     * @return USERID
     **/
    public String getUSERID() {
        return USERID;
    }

    /**
     * Sets the value of USERID.
     *
     * @param USERID the new value
     */
    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    /**
     * Gets IDFILM
     *
     * @return IDFILM
     **/
    public String getIDFILM() {
        return IDFILM;
    }

    /**
     * Sets the value of IDFILM.
     *
     * @param IDFILM the new value
     */
    public void setIDFILM(String IDFILM) {
        this.IDFILM = IDFILM;
    }

}
