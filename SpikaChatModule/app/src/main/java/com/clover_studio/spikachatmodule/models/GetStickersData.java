package com.clover_studio.spikachatmodule.models;

import com.clover_studio.spikachatmodule.base.BaseModel;

import java.util.List;

/**
 * Created by ubuntu_ivo on 22.07.15..
 */
public class GetStickersData extends BaseModel {

    public GetStickersInsideData data;

    public class GetStickersInsideData{
        public List<StickerCategory> stickers;
    }
}
