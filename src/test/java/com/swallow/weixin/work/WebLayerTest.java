package com.swallow.weixin.work;

import com.swallow.weixin.work.controller.WeixinController;
import com.swallow.weixin.work.service.AccessTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeixinController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@Import(WeixinWorkApplication.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccessTokenService accessTokenService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        given(this.accessTokenService.get(anyString())).willReturn(anyString());
        this.mockMvc.perform(get("/getAccessToken"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")))
                .andDo(document("index"));
    }

}
