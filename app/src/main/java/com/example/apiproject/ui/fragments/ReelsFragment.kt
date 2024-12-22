package com.example.apiproject.ui.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apiproject.data.api.ExtractedData
import com.example.apiproject.data.api.Video
import com.example.apiproject.databinding.FragmentReelsBinding
import com.example.apiproject.domain.extractor.types.ExtractorManager
import com.example.apiproject.ui.activity.MainActivity
import com.example.apiproject.util.SocialMediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReelsFragment : Fragment() {

//    private var scriptButton =
//        "data:image/png;base64,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"

    private var scriptButton =
        "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA5gAAAOYCAYAAACwyFy9AAA4EUlEQVR4nO3dTY4aXZaA4ZulXEFKSMQWWFiOvAMGiEHtoEYszEtwIFnqLdADOzCQZCZE3Ij79zyTri4VPnTXZ7gvJwheTqfTKYx07PuxDw3rrhv9WHPNNddcc80111xzzTXXXHPzm/uf0X8aAAAAXBCYAAAARCEwAQAAiEJgAgAAEIXABAAAIAqBCQAAQBQCEwAAgCgEJgAAAFEITAAAAKIQmAAAAEQhMAEAAIhCYAIAABCFwAQAACAKgQkAAEAUr8e+H/3gddeNfqy55pprrrnmmmuuueaaa665dc21wQQAACAKgQkAAEAUAhMAAIAoBCYAAABRCEwAAACiEJgAAABEITABAACIQmACAAAQhcAEAAAgCoEJAABAFAITAACAKAQmAAAAUQhMAAAAohCYAAAARPFyOp1OYx987PvRg9ddN/qx5pprrrnmmmuuueaaa6655uY31wYTAACAKAQmAAAAUQhMAAAAohCYAAAARCEwAQAAiEJgAgAAEIXABAAAIAqBCQAAQBQCEwAAgCgEJgAAAFEITAAAAKIQmAAAAEQhMAEAAIhCYAIAABDF67HvRz943XWjH2uuueaaa6655pprrrnmmmtuXXNtMAEAAIhCYAIAABCFwAQAACAKgQkAAEAUAhMAAIAoBCYAAABRCEwAAACiEJgAAABEITABAACIQmACAAAQhcAEAAAgCoEJAABAFAITAACAKAQmAAAAUbycTqfT2Acf+3704HXXjX6sueaaa6655pprrrnmmmuuufnNtcEEAAAgCoEJAABAFAITAACAKAQmAAAAUQhMAAAAohCYAAAARCEwAQAAiEJgAgAAEIXABAAAIAqBCQAAQBQCEwAAgCgEJgAAAFEITAAAAKIQmAAAAETxeuz70Q9ed93ox5prrrnmmmuuueaaa6655ppb11wbTAAAAKIQmAAAAEQhMAEAAIhCYAIAABCFwAQAACAKgQkAAEAUAhMAAIAoBCYAAABRCEwAAACiEJgAAABEITABAACIQmACAAAQhcAEAAAgCoEJAABAFC+n0+k09sHHvh89eN11ox9rrrnmmmuuueaaa6655pprbn5zbTABAACI4jX1EwCAHBz7Pqy77qFPgR/9zwFAawQmAEW6Dbz14RCO7+9X//MZ65v/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"

    private var appType = SocialMediaType.FB_WATCH.name

    private val binding by lazy {
        FragmentReelsBinding.inflate(layoutInflater)
    }


    private var backPressedCallback: OnBackPressedCallback? = null

    private fun configureBackPress() {
        backPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backPressedCallback!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        configureBackPress()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.let {
            if (it is MainActivity) {
                it.hideTopAndBottomBar()
            }
        }

        init()
        initListeners()
    }

    private fun init() {

        initializeWebView()

        when (appType) {

            SocialMediaType.FB_WATCH.name -> {

                binding.webView.loadUrl("https://m.facebook.com/watch/")

                binding.webView.webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)

                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvLink.text = url

                    }

                    override fun onLoadResource(view: WebView?, url: String?) {
                        super.onLoadResource(view, url)

                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)

                        binding.webView.loadUrl(
                            "javascript:" +
                                    "window.onclick=function()\n " +
                                    "{\n " +
                                    "newJava.logs(\"onclick\");" +
                                    "var ij=document.getElementsByClassName(\"img\");\n" +
                                    "newJava.logs(\"started\");" +
                                    "if(ij.length > 0){" +
                                    "try {" +
                                    " for(var f=0;f<ij.length;f++)\n" +
                                    "  {\n" +
                                    "      var dbutton=ij[f].parentNode.querySelector(\"#download-button\") != null;" +
                                    "     if(!dbutton)" +
                                    "    {" +
                                    "      if(ij[f].parentNode.attributes.getNamedItem(\"data-video-url\") !=null)" +
                                    "      {" +

                                    "      var data = ij[f].parentNode.attributes.getNamedItem(\"data-video-url\").value; " +
                                    "      var json = ij[f].parentNode.attributes.getNamedItem(\"data-video-id\").value; " +
                                    "      var isReel = ij[f].parentNode.attributes.getNamedItem(\"data-is-reels\").value; " +
                                    "      var DOM_img = document.createElement(\"img\");\n" +
                                    "      DOM_img.height=60;\n" +
                                    "      DOM_img.id=\"download-button\";\n" +
                                    "      DOM_img.width=60;\n" +
                                    "      DOM_img.name=data.toString();\n" +
                                    "      DOM_img.longDesc=json.toString();" +
                                    "      DOM_img.zIndex=1000;\n" +
                                    "      DOM_img.style.position=\"absolute\";\n" +
                                    "      DOM_img.style.marginLeft=\"20px\";\n" +
                                    "      if(!isReel){ DOM_img.style.marginTop=\"40px\";}else{DOM_img.style.marginTop=\"150px\";} \n" +
                                    "      DOM_img.src =\"$scriptButton\";\n" +
                                    "      DOM_img.style.background = \"transparent\";  \n" +
                                    "      DOM_img.style.clipPath = \"circle(40%)\";  \n" +
                                    "      DOM_img.style.overflow = \"hidden\"; \n" +
                                    "      DOM_img.addEventListener(\"click\",function(){" +
                                    "       event.stopPropagation();" +
                                    "      var title=document.title;" +
                                    "      newJava.scriptData(this.name.toString(),this.name.toString(),\"Facebook\",title,this.longDesc.toString())" +
                                    "      });\n" +
                                    "      ij[f].parentNode.appendChild(DOM_img);\n" +
                                    "       \n" +
                                    "       }" +
                                    "     else{" +
                                    "      var imageUrl = ij[f].src;" +
                                    "      if(imageUrl !=null){" +
                                    "      if(!imageUrl.endsWith(\".png\") && ij[f].className != \"img contain ds\" &&  ij[f].className != \"img cover rounded gray-border\" && ij[f].className != \"img contain rounded gray-border\" ){" +
                                    "       var DOM_img = document.createElement(\"img\");\n" +
                                    "       DOM_img.height=60;\n" +
                                    "       DOM_img.id=\"download-button\";\n" +
                                    "      DOM_img.width=60;\n" +
                                    "      DOM_img.name=imageUrl.toString();\n" +
                                    "      DOM_img.longDesc=imageUrl.toString();" +
                                    "      DOM_img.zIndex=1000;\n" +
                                    "      if(isReel){ DOM_img.style.marginTop=\"20px\";}else{DOM_img.style.marginTop=\"100px\";} \n" +
                                    "      DOM_img.style.position=\"absolute\";\n" +
                                    "      DOM_img.style.marginLeft=\"20px\";\n" +
                                    "      DOM_img.style.pointerEvents = \"initial\";" +
                                    "      DOM_img.style.backgroundColor = \"red\"; " +
                                    "      DOM_img.src =\"$scriptButton\";\n" +
                                    "      DOM_img.style.background = \"transparent\";  \n" +
                                    "      DOM_img.style.clipPath = \"circle(40%)\";  \n" +
                                    "      DOM_img.style.overflow = \"hidden\"; \n" +
                                    "      DOM_img.addEventListener(\"click\",function(){" +
                                    "       event.stopPropagation();" +
                                    "      var title=document.title;" +
                                    "      newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"Facebook\",title,\"\")" +
                                    "      });\n" +
                                    "      ij[f].parentNode.appendChild(DOM_img);\n" +
                                    "        }" +
                                    "}}" +
                                    " }" +
                                    "}" +
                                    "}" +
                                    "catch (error) {" +
                                    "" +
                                    "}" +
                                    "}" +
                                    "}; " +


                                    "window.onscroll=function()\n " +
                                    "{\n " +
                                    "var ij=document.getElementsByClassName(\"img\");\n" +
                                    "newJava.logs(\"started\");" +
                                    "if(ij.length > 0){" +
                                    "try {" +
                                    " for(var f=0;f<ij.length;f++)\n" +
                                    "  {\n" +
                                    "      var dbutton=ij[f].parentNode.querySelector(\"#download-button\") != null;" +
                                    "     if(!dbutton)" +
                                    "    {" +
                                    "      if(ij[f].parentNode.attributes.getNamedItem(\"data-video-url\") !=null)" +
                                    "      {" +

                                    "      var data = ij[f].parentNode.attributes.getNamedItem(\"data-video-url\").value; " +
                                    "      var json = ij[f].parentNode.attributes.getNamedItem(\"data-video-id\").value; " +
                                    "      var isReel = ij[f].parentNode.attributes.getNamedItem(\"data-is-reels\").value; " +
                                    "      var DOM_img = document.createElement(\"img\");\n" +
                                    "      DOM_img.height=60;\n" +
                                    "      DOM_img.id=\"download-button\";\n" +
                                    "      DOM_img.width=60;\n" +
                                    "      DOM_img.name=data.toString();\n" +
                                    "      DOM_img.longDesc=json.toString();" +
                                    "      DOM_img.zIndex=1000;\n" +
                                    "      DOM_img.style.position=\"absolute\";\n" +
                                    "      DOM_img.style.marginLeft=\"20px\";\n" +
                                    "      if(!isReel){ DOM_img.style.marginTop=\"40px\";}else{DOM_img.style.marginTop=\"150px\";} \n" +
                                    "      DOM_img.src =\"$scriptButton\";\n" +
                                    "      DOM_img.style.background = \"transparent\";  \n" +
                                    "      DOM_img.style.clipPath = \"circle(40%)\";  \n" +
                                    "      DOM_img.style.overflow = \"hidden\"; \n" +
                                    "      DOM_img.addEventListener(\"click\",function(){" +
                                    "       event.stopPropagation();" +
                                    "      var title=document.title;" +
                                    "      newJava.scriptData(this.name.toString(),this.name.toString(),\"Facebook\",title,this.longDesc.toString())" +
                                    "      });\n" +
                                    "      ij[f].parentNode.appendChild(DOM_img);\n" +
                                    "       \n" +
                                    "       }" +
                                    "     else{" +
                                    "      var imageUrl = ij[f].src;" +
                                    "      if(imageUrl !=null){" +
                                    "      if(!imageUrl.endsWith(\".png\") && ij[f].className != \"img contain ds\" &&  ij[f].className != \"img cover rounded gray-border\" && ij[f].className != \"img contain rounded gray-border\" ){" +
                                    "       var DOM_img = document.createElement(\"img\");\n" +
                                    "       DOM_img.height=60;\n" +
                                    "       DOM_img.id=\"download-button\";\n" +
                                    "      DOM_img.width=60;\n" +
                                    "      DOM_img.name=imageUrl.toString();\n" +
                                    "      DOM_img.longDesc=imageUrl.toString();" +
                                    "      DOM_img.zIndex=1000;\n" +
                                    "      if(isReel){ DOM_img.style.marginTop=\"20px\";}else{DOM_img.style.marginTop=\"100px\";} \n" +
                                    "      DOM_img.style.position=\"absolute\";\n" +
                                    "      DOM_img.style.marginLeft=\"20px\";\n" +
                                    "      DOM_img.style.pointerEvents = \"initial\";" +
                                    "      DOM_img.src =\"$scriptButton\";\n" +
                                    "      DOM_img.style.background = \"transparent\";  \n" +
                                    "      DOM_img.style.clipPath = \"circle(40%)\";  \n" +
                                    "      DOM_img.style.overflow = \"hidden\"; \n" +
                                    "      DOM_img.addEventListener(\"click\",function(){" +
                                    "       event.stopPropagation();" +
                                    "      var title=document.title;" +
                                    "      newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"Facebook\",title,\"\")" +
                                    "      });\n" +
                                    "      ij[f].parentNode.appendChild(DOM_img);\n" +
                                    "        }" +
                                    "}}" +
                                    " }" +
                                    "}" +
                                    "}" +
                                    "catch (error) {" +
                                    "" +
                                    "}" +
                                    "}" +
                                    "}; "
                        )

                        binding.progressBar.visibility = View.GONE
                        binding.tvLink.text = url


                    }

                }

            }

            SocialMediaType.INSTA_REELS.name -> {

                binding.webView.loadUrl("https://www.instagram.com/explore/")

                binding.webView.webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvLink.text = url
                    }

                    override fun onLoadResource(view: WebView?, url: String?) {
                        super.onLoadResource(view, url)

                        binding.webView
                            .loadUrl(
                                "javascript:" +

                                        "try {" +
                                        "var doc = document.getElementsByClassName(\"x5yr21d x10l6tqk x13vifvy xh8yej3\");\n" +
                                        "for (var f = 0; f < doc.length; f++) {\n" +
                                        "    var DButton = doc[f].querySelector(\"#download-button-video\") != null;\n" +
                                        "    if (!DButton) {\n" +
                                        "        var Download_Img = document.createElement(\"img\");\n" +
                                        "        var baseuri = doc[f].parentNode.parentNode.childNodes[0].baseURI.toString;\n" +
                                        "        var videourl = doc[f].parentNode.parentNode.childNodes[0].src;\n" +
                                        "        Download_Img.style.height=\"60px\";\n" +
                                        "        Download_Img.style.width=\"60px\";\n" +
                                        "        Download_Img.id=\"download-button-video\";\n" +
                                        "        Download_Img.name=baseuri;\n" +
                                        "        Download_Img.longDesc=videourl;\n" +
                                        "        Download_Img.style.position=\"absolute\";\n" +
                                        "        Download_Img.style.marginLeft=\"10px\";\n" +
                                        "        Download_Img.style.marginBottom=\"50px\";\n" +
                                        "        Download_Img.style.marginTop=\"50px\";\n" +
                                        "        Download_Img.style.marginRight=\"10px\";\n" +
                                        "        Download_Img.style.zIndex=10;\n" +
                                        "        Download_Img.zIndex=10;\n" +
                                        "        Download_Img.src =\"$scriptButton\";\n" +
                                        "      Download_Img.style.background = \"transparent\";  \n" +
                                        "      Download_Img.style.clipPath = \"circle(40%)\";  \n" +
                                        "      Download_Img.style.overflow = \"hidden\"; \n" +
                                        "        Download_Img.addEventListener(\"click\",function(){\n" +
                                        "            var title=document.title;\n" +
                                        "            newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"instagram\",title,\"\");\n" +
                                        "        });\n" +
                                        "        doc[f].appendChild(Download_Img)\n" +
                                        "    } else{}\n" +
                                        "}" +
                                        "} catch (error) {" +
                                        "" +
                                        "}" +
                                        "try {" +
                                        "var ij=document.getElementsByClassName(\"_aagv\");\n" +
                                        "    for(var f=0;f<ij.length;f++)\n" +
                                        "    {\n" +
                                        "      var dbutton=ij[f].querySelector(\"#download-button\") != null;" +
                                        "     if(!dbutton){" +
                                        "      var DOM_img = document.createElement(\"img\");\n" +
                                        "      var baseuri=ij[f].childNodes[0].baseURI;" +
                                        "      var videourl=ij[f].childNodes[0].src;" +
                                        "      DOM_img.style.height=\"60px\";\n" +
                                        "      DOM_img.style.width=\"60px\";\n" +
                                        "      DOM_img.style.zIndex=7;\n" +
                                        "      DOM_img.bottom=10;\n" +
                                        "      DOM_img.name=baseuri.toString();\n" +
                                        "      DOM_img.longDesc=videourl.toString();\n" +
                                        "      DOM_img.id=\"download-button\";\n" +
                                        "      DOM_img.style.position=\"relative\";\n" +
                                        "      DOM_img.style.marginLeft=\"10px\";\n" +
                                        "      DOM_img.style.marginTop=\"10px\";\n" +
                                        "      DOM_img.src =\"$scriptButton\";\n" +
                                        "      DOM_img.style.background = \"transparent\";  \n" +
                                        "      DOM_img.style.clipPath = \"circle(40%)\";  \n" +
                                        "      DOM_img.style.overflow = \"hidden\"; \n" +
                                        "      DOM_img.addEventListener(\"click\",function(){" +
                                        "       var title=document.title;" +
                                        "      newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"instagram\",title,\"\")});\n" +
                                        "      ij[f].appendChild(DOM_img);\n" +
                                        "       \n" +
                                        "    }" +
                                        "}" +
                                        "}" +
                                        " catch (error) {" +
                                        "" +
                                        "}" +
                                        "try {" +
                                        "var ij=document.getElementsByClassName(\"KL4Bh\");\n" +
                                        "    for(var f=0;f<ij.length;f++)\n" +
                                        "    {\n" +
                                        "      var dbutton=ij[f].querySelector(\"#download-button\") != null;" +
                                        "     if(!dbutton){" +
                                        "      var DOM_img = document.createElement(\"img\");\n" +
                                        "      var baseuri=ij[f].childNodes[0].baseURI;" +
                                        "      var videourl=ij[f].childNodes[0].src;" +
                                        "      DOM_img.style.height=\"60px\";\n" +
                                        "      DOM_img.style.width=\"60px\";\n" +
                                        "      DOM_img.style.zIndex=7;\n" +
                                        "      DOM_img.bottom=10;\n" +
                                        "      DOM_img.name=baseuri.toString();\n" +
                                        "      DOM_img.longDesc=videourl.toString();\n" +
                                        "      DOM_img.id=\"download-button\";\n" +
                                        "      DOM_img.style.position=\"relative\";\n" +
                                        "      DOM_img.style.marginLeft=\"10px\";\n" +
                                        "      DOM_img.style.marginTop=\"10px\";\n" +
                                        "      DOM_img.src =\"$scriptButton\";\n" +
                                        "      DOM_img.style.background = \"transparent\";  \n" +
                                        "      DOM_img.style.clipPath = \"circle(40%)\";  \n" +
                                        "      DOM_img.style.overflow = \"hidden\"; \n" +
                                        "      DOM_img.addEventListener(\"click\",function(){" +
                                        "       var title=document.title;" +
                                        "      newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"instagram\",title,\"\")});\n" +
                                        "      ij[f].appendChild(DOM_img);\n" +
                                        "       \n" +
                                        "    }" +
                                        "}" +
                                        "} catch (error) {" +
                                        "" +
                                        "}"
                            )

                    }

                    /*override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)

                        binding.progressBar.visibility = View.GONE
                        binding.tvLink.text = url

                        binding.webView
                            .loadUrl(
                                "javascript:" +

                                        "try {" +
                                        "var doc = document.getElementsByClassName(\"x5yr21d x10l6tqk x13vifvy xh8yej3\");\n" +
                                        "for (var f = 0; f < doc.length; f++) {\n" +
                                        "    var DButton = doc[f].querySelector(\"#download-button-video\") != null;\n" +
                                        "    if (!DButton) {\n" +
                                        "        var Download_Img = document.createElement(\"img\");\n" +
                                        "        var baseuri = doc[f].parentNode.parentNode.childNodes[0].baseURI.toString;\n" +
                                        "        var videourl = doc[f].parentNode.parentNode.childNodes[0].src;\n" +
                                        "        Download_Img.style.height=\"60px\";\n" +
                                        "        Download_Img.style.width=\"60px\";\n" +
                                        "        Download_Img.id=\"download-button-video\";\n" +
                                        "        Download_Img.name=baseuri;\n" +
                                        "        Download_Img.longDesc=videourl;\n" +
                                        "        Download_Img.style.position=\"absolute\";\n" +
                                        "        Download_Img.style.marginLeft=\"10px\";\n" +
                                        "        Download_Img.style.marginBottom=\"50px\";\n" +
                                        "        Download_Img.style.marginTop=\"50px\";\n" +
                                        "        Download_Img.style.marginRight=\"10px\";\n" +
                                        "        Download_Img.style.zIndex=10;\n" +
                                        "        Download_Img.zIndex=10;\n" +
                                        "        Download_Img.src =\"$scriptButton\";\n" +
                                        "        Download_Img.addEventListener(\"click\",function(){\n" +
                                        "            var title=document.title;\n" +
                                        "            Android.showToast(\"Button clicked!\");"+
                                        "            newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"instagram\",title,\"\");\n" +
                                        "        });\n" +
                                        "        doc[f].appendChild(Download_Img)\n" +
                                        "    } else{}\n" +
                                        "}" +
                                        "} catch (error) {" +
                                        "" +
                                        "}" +
                                        "try {" +
                                        "var ij=document.getElementsByClassName(\"_aagv\");\n" +
                                        "    for(var f=0;f<ij.length;f++)\n" +
                                        "    {\n" +
                                        "      var dbutton=ij[f].querySelector(\"#download-button\") != null;" +
                                        "     if(!dbutton){" +
                                        "      var DOM_img = document.createElement(\"img\");\n" +
                                        "      var baseuri=ij[f].childNodes[0].baseURI;" +
                                        "      var videourl=ij[f].childNodes[0].src;" +
                                        "      DOM_img.style.height=\"60px\";\n" +
                                        "      DOM_img.style.width=\"60px\";\n" +
                                        "      DOM_img.style.zIndex=7;\n" +
                                        "      DOM_img.bottom=10;\n" +
                                        "      DOM_img.name=baseuri.toString();\n" +
                                        "      DOM_img.longDesc=videourl.toString();\n" +
                                        "      DOM_img.id=\"download-button\";\n" +
                                        "      DOM_img.style.position=\"relative\";\n" +
                                        "      DOM_img.style.marginLeft=\"10px\";\n" +
                                        "      DOM_img.style.marginTop=\"10px\";\n" +
                                        "      DOM_img.src =\"$scriptButton\";\n" +
                                        "      DOM_img.addEventListener(\"click\",function(){" +
                                        "       var title=document.title;" +
                                        "      newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"instagram\",title,\"\")});\n" +
                                        "      ij[f].appendChild(DOM_img);\n" +
                                        "       \n" +
                                        "    }" +
                                        "}" +
                                        "}" +
                                        " catch (error) {" +
                                        "" +
                                        "}" +
                                        "try {" +
                                        "var ij=document.getElementsByClassName(\"KL4Bh\");\n" +
                                        "    for(var f=0;f<ij.length;f++)\n" +
                                        "    {\n" +
                                        "      var dbutton=ij[f].querySelector(\"#download-button\") != null;" +
                                        "     if(!dbutton){" +
                                        "      var DOM_img = document.createElement(\"img\");\n" +
                                        "      var baseuri=ij[f].childNodes[0].baseURI;" +
                                        "      var videourl=ij[f].childNodes[0].src;" +
                                        "      DOM_img.style.height=\"60px\";\n" +
                                        "      DOM_img.style.width=\"60px\";\n" +
                                        "      DOM_img.style.zIndex=7;\n" +
                                        "      DOM_img.bottom=10;\n" +
                                        "      DOM_img.name=baseuri.toString();\n" +
                                        "      DOM_img.longDesc=videourl.toString();\n" +
                                        "      DOM_img.id=\"download-button\";\n" +
                                        "      DOM_img.style.position=\"relative\";\n" +
                                        "      DOM_img.style.marginLeft=\"10px\";\n" +
                                        "      DOM_img.style.marginTop=\"10px\";\n" +
                                        "      DOM_img.src =\"$scriptButton\";\n" +
                                        "      DOM_img.addEventListener(\"click\",function(){" +
                                        "       var title=document.title;" +
                                        "      newJava.scriptData(this.name.toString(),this.longDesc.toString(),\"instagram\",title,\"\")});\n" +
                                        "      ij[f].appendChild(DOM_img);\n" +
                                        "       \n" +
                                        "    }" +
                                        "}" +
                                        "} catch (error) {" +
                                        "" +
                                        "}"
                            )
                    }*/

                }


            }

            else -> {
                findNavController().popBackStack()
            }
        }

    }

    @JavascriptInterface
    fun scriptData(
        VideoPostUrl: String,
        pathVideo: String,
        Platform: String,
        videoTitle: String,
        anyJson: String
    ) {
        Log.i(
            "NewMyData",
            "scriptData: $VideoPostUrl ====> $pathVideo======> $Platform titlt====>$videoTitle ====>$anyJson"
        )

        lifecycleScope.launch(Dispatchers.IO) {
            if (Platform.contains("facebook", true)) {
                val name = if (videoTitle.equals("Facebook Watch", true)) {
                    videoTitle.replace(" ", "_") + "_${System.currentTimeMillis()}"
                } else {
                    videoTitle
                }
                if (!pathVideo.contains("playlist.m3u8", true)) {
                    try {
                        withContext(Dispatchers.Main) {
                            if (anyJson.isEmpty()) {
                                // LinkFetcherLoading.hideLoadingDialog()
                                /*withContext(Dispatchers.Main) {
                                    if (pathVideo != "") {
                                        val videoData = VideoUrls("jpg", "jpg", "jpg", pathVideo)
                                        val apidatalist = VideoApiData(
                                            "success",
                                            "",
                                            "facebook",
                                            name,
                                            pathVideo,
                                            "",
                                            "",
                                            mutableListOf<VideoUrls>(videoData)
                                        )
                                        DownloadDialog(apidatalist, VideoPostUrl, pathVideo)
                                        LinkFetcherLoading.hideLoadingDialog()
                                    } else {
                                        Toast.makeText(
                                            itContext,
                                            resources.getString(R.string.some_error_occured),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }*/

                                activity.let {
                                    if (it is MainActivity) {
                                        var video = Video(200, "d", 1, VideoPostUrl, 200, "null")
                                        it.downloadOptionSheet(
                                            ExtractedData(listOf(video),
                                                title="Video_" + (1..1000000).random().toString(),
                                            ),
                                            VideoPostUrl
                                        )
                                    }
                                }

                                withContext(Dispatchers.Main) {
                                    //downloadVideo(anyJson)

                                    activity.let {
                                        if (it is MainActivity) {
                                            var video =
                                                Video(200, "d", 1, VideoPostUrl, 200, "null")
                                            it.downloadOptionSheet(
                                                ExtractedData(listOf(video),
                                                    title="Video_" + (1..1000000).random().toString(),
                                                ),
                                                VideoPostUrl
                                            )
                                        }
                                    }

                                    val result =
                                        ExtractorManager.getVideo(requireContext(), anyJson)
                                    if (result != null) {
                                        Log.d(
                                            "RESULT_TAG",
                                            "scriptData: $result , title is ${result.title}"
                                        )
                                    }
                                }
                            } else {
                                withContext(Dispatchers.Main) {
                                    val videoid = anyJson.substringAfterLast("/")
                                    val url = "https://www.facebook.com/watch/?v=$videoid"
                                    //  downloadFBData(url, pathVideo, name)
                                    withContext(Dispatchers.Main) {
                                        activity.let {
                                            if (it is MainActivity) {
                                                var video =
                                                    Video(200, "d", 1, VideoPostUrl, 200, "null")
                                                it.downloadOptionSheet(
                                                    ExtractedData(listOf(video),
                                                        title="Video_" + (1..1000000).random().toString(),
                                                    ),
                                                    VideoPostUrl
                                                )
                                            }
                                        }
                                        // downloadVideo(url)
                                        val result =
                                            ExtractorManager.getVideo(requireContext(), url)
                                        if (result != null) {
                                            Log.d(
                                                "RESULT_TAG",
                                                "scriptData: $result , title is ${result.title}"
                                            )
                                        }
                                    }
                                }

                            }
                        }


                    } catch (e: Exception) {
                        /*withContext(Dispatchers.Main) {
                            Toast.makeText(
                                itContext,
                                resources.getString(R.string.some_error_try_again),
                                Toast.LENGTH_SHORT
                            ).show()*/
                    }
                }
            } else if (Platform.contains("instagram", true)) {
                var name = if (videoTitle.equals("Instagram", true)) {
                    if (pathVideo.contains(".jpg", true)) {
                        "Instagram_image_${System.currentTimeMillis()}"
                    } else {
                        "Instagram_video_${System.currentTimeMillis()}"
                    }
                } else {
                    videoTitle
                }
                var quality = if (pathVideo.contains(".jpg", true) || pathVideo.contains(
                        "-jpg",
                        true
                    )
                ) {
                    "jpg"
                } else {
                    "480 "
                }
                var ext = if (pathVideo.contains(".jpg", true) || pathVideo.contains(
                        "-jpg",
                        true
                    )
                ) {
                    "jpg"
                } else {
                    "mp4"
                }
                try {
                    withContext(Dispatchers.Main) {
                        Log.i("zsdfilbsfilc", "getData: $pathVideo ---- $VideoPostUrl")

                        activity.let {
                            if (it is MainActivity) {
                                var video = Video(200, "d", 1, VideoPostUrl, 200, "null")
                                it.downloadOptionSheet(
                                    ExtractedData(
                                         listOf(video),
                                        title="Video_" + (1..1000000).random().toString(),
                                        ), VideoPostUrl
                                )
                            }
                        }
                        //LinkFetcherLoading.hideLoadingDialog()
                        /*if (pathVideo != "") {
                            var videoData = VideoUrls(quality, quality, ext, pathVideo)
                            var apidatalist = VideoApiData(
                                VideoPostUrl,
                                "success",
                                "",
                                "instagram",
                                name,
                                pathVideo,
                                "",
                                "",
                                mutableListOf<VideoUrls>(videoData)
                            )

                            Log.d(TAG,pathVideo.toString())
                            showDownloadDialogNewUi(apidatalist)
                        }*/
                    }


                } catch (e: Exception) {

                    withContext(Dispatchers.Main) {
                        /*LinkFetcherLoading.hideLoadingDialog()
                        Toast.makeText(
                            itContext,
                            resources.getString(R.string.some_error_try_again),
                            Toast.LENGTH_SHORT
                        ).show()*/
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    /*   LinkFetcherLoading.hideLoadingDialog()
                       Toast.makeText(
                           itContext,
                           resources.getString(R.string.live_vide_not_downlaod_able),
                           Toast.LENGTH_SHORT
                       ).show()*/
                }
            }
        }
    }

    @JavascriptInterface
    fun logs(message: String) {
        Log.d("JSM", "message : " + message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.webView.clearCache(true)
        binding.webView.clearHistory()
        binding.webView.clearFormData()
        binding.webView.removeAllViews()
        backPressedCallback?.remove()
        backPressedCallback = null
    }


    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    private fun initializeWebView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.allowContentAccess = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.addJavascriptInterface(this, "newJava")
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.builtInZoomControls = false
        binding.webView.settings.displayZoomControls = false
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.mediaPlaybackRequiresUserGesture = true
        binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true
    }

    private fun initListeners() {
        binding.backButton.setOnClickListener {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                findNavController().popBackStack()
            }
        }
    }

}