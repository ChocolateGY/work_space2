package com.talkingdata

/**
  * Created by root on 2018-4-3.
  */


import com.talkingdata.client.TDClient
import com.talkingdata.client.config.TDSetting
import com.talkingdata.client.constant.HttpConstants
import com.talkingdata.client.entity.Request
import java.io.IOException
import java.net.URISyntaxException
import java.util
import java.util.{Arrays, HashMap, List, Map}


object Sdmk {
  @throws[IOException]
  @throws[URISyntaxException]
  def main(args: String*): Unit = { //填入你的apikey 以及 apiToken
    val apiKey = "b3d0262c132142c289c58911f6cd7670"
    val apiToken = "d7424ed15cc1443384b9caae203869eb"
    TDSetting.setting(apiKey, apiToken, "https://api.talkingdata.com/tdmkaccount/authen/app/v2")
    //        TDSetting.setting(apiKey, apiToken, "https://api.talkingdata.com/tdmkaccount/authen/app/v3");
    //https://api.talkingdata.com/data/user-tag-demographic/v1?id=3c9251ecc0dc1cf2cec477354516d9c8c&type=tdid
    //更换需要访问的apiUrl
    //        String url = "https://api.talkingdata.com/data/user-tag-demographic/v1";
    //        //queryString 都放在map中 也就是?之后的
    //        Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
    //        queryParams.put("id", Arrays.asList("3c9251ecc0dc1cf2cec477354516d9c8c"));
    //        queryParams.put("type", Arrays.asList("tdid"));
    val url = "https://api.talkingdata.com/data/17/sh-cupi/restFul/sexAgeQuery"
    val queryParams = new util.HashMap[String, util.List[String]]
    queryParams.put("imei", util.Arrays.asList("86760002137080")) //354765081282246,35695206828974

    queryParams.put("imsi", util.Arrays.asList("460011681609344"))
    val request = new Request(url, queryParams, HttpConstants.GET)
    val ex = TDClient.request(request, classOf[String])
    System.out.println(ex)
  }
}
