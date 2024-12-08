package com.luciano.microservicocadastrarclient.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters
            .filterIsInstance<MappingJackson2HttpMessageConverter>()
            .forEach { converter ->
                converter.supportedMediaTypes = converter.supportedMediaTypes +
                        org.springframework.http.MediaType.valueOf("application/json;charset=UTF-8")
            }
    }
}
