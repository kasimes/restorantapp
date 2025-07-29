package com.ornek.restorant.restorantapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ornek.restorant.restorantapp.model.dto.BranchDto;

import com.ornek.restorant.restorantapp.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.*;








import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BranchController.class)
public class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Autowired
    private ObjectMapper objectMapper;

    private BranchDto  branchDto;
    private BranchDto  branchDto1;

    @BeforeEach
    void setUp() {
        branchDto = new BranchDto();
        branchDto.setId(1L);
        branchDto.setName("Branch 1");

        branchDto1 =  new BranchDto();
        branchDto1.setId(2L);
        branchDto1.setName("Branch 2");

    }
    @Test
    void testGetAllBranches() throws Exception {
        List<BranchDto> branchDtoList = Arrays.asList(branchDto,branchDto1);
        Mockito.when(branchService.getAllBranches()).thenReturn(branchDtoList);

        mockMvc.perform(get("/api/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Branch 1")));


    }

    @Test
    void testGetBranchById() throws Exception {
        Mockito.when(branchService.getBranchById(1L)).thenReturn(branchDto);
        mockMvc.perform(get("/api/branches/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name", is("Branch 1")));


    }

    @Test
    void testCreateBranch() throws Exception {
        Mockito.when(branchService.saveBranch(Mockito.any(BranchDto.class))).thenReturn(branchDto);

        mockMvc.perform(post("/api/branches")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(branchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Branch 1"));
    }


    @Test
    void testUpdateBranch() throws Exception {
        BranchDto updatedBranch = new BranchDto();
        updatedBranch.setId(1L);
        updatedBranch.setName("Updated Branch");

        when(branchService.updateBranch(eq(1L), any(BranchDto.class)))
                .thenReturn(updatedBranch);

        mockMvc.perform(put("/api/branches/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBranch)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Branch"));
    }

    @Test
    void testDeleteBranch() throws Exception {
        doNothing().when(branchService).deleteBranch(1L);

        mockMvc.perform(delete("/api/branches/{id}", 1L))
                .andExpect(status().isNoContent());
    }




}
