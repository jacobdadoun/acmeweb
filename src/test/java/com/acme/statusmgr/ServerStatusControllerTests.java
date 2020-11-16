/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }

    // curl http://localhost:8080/server/status/detailed
    //{"timestamp":"2019-11-06T07:20:47.663+0000","status":400,"error":"Bad Request","message":"Required List parameter 'details' is not present","path":"/server/status/detailed"}

    @Test
    public void testCaseOne() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory&name=yaakov"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by yaakov"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is running low"));

    }

    @Test
    public void testCaseTwo() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally"));

    }

    @Test
    public void testCaseThree() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,extensions"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));

    }

    @Test
    public void testCaseFour() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));

    }

    @Test
    public void testCaseFive() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,extension,memory&name=Brian"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Brian"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));

    }

    @Test
    public void testCaseSix() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=operations,memory&name=Noach"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and its memory is running low"));

    }

    @Test
    public void testCaseSeven() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=extensions,memory&name=Jack"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Jack"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));

    }

    @Test
    public void testCaseEight() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?name=Derek&details=extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Derek"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));

    }

    @Test
    public void testCaseNine() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed?details=memory,operations,extensions,memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is running low, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));

    }

//    @Test
//    public void testCaseTen() throws Exception {
//        this.mockMvc.perform(get("/server/status/detailed?details=memory,operations,extensions,memory"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
//                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is running low, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
//
//    }

}
