package com.greenfox.tribesoflagopusandroid.api.model.response;


import com.greenfox.tribesoflagopusandroid.api.model.gameobject.Resource;

import java.util.List;

/**
 * Created by User on 2017. 06. 15..
 */

public class ResourcesResponse extends BaseResponse {

    private List<Resource> resources;

    public ResourcesResponse(List<Resource> resources) {
        this.resources = resources;
    }

    public ResourcesResponse() {
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
