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

import java.util.*;
import apiRest_DynamoDB.model.OutputItem;
import apiRest_DynamoDB.model.OutputItemsItem;

public class Output {
    @com.google.gson.annotations.SerializedName("Items")
    private List<OutputItemsItem> items = null;
    @com.google.gson.annotations.SerializedName("Item")
    private OutputItem item = null;

    /**
     * Gets items
     *
     * @return items
     **/
    public List<OutputItemsItem> getItems() {
        return items;
    }

    /**
     * Sets the value of items.
     *
     * @param items the new value
     */
    public void setItems(List<OutputItemsItem> items) {
        this.items = items;
    }

    /**
     * Gets item
     *
     * @return item
     **/
    public OutputItem getItem() {
        return item;
    }

    /**
     * Sets the value of item.
     *
     * @param item the new value
     */
    public void setItem(OutputItem item) {
        this.item = item;
    }

}
