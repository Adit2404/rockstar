package com.example.music.rest;

import com.example.music.domain.Song;
import com.example.music.service.MusicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class MusicControllerTest {

    @Mock
    private MusicService musicService;

    @InjectMocks
    private MusicController musicController;

    private MockMvc mockMvc;

    @Test
    void findBandByName() throws Exception {
        mockMvc = standaloneSetup(musicController).build();

        when(musicService.findByName(anyString())).thenReturn(new Song(807L, "Distracted", "Pop-Rock", 2009, "KSM", "Read Between the Lines", "129", "1v47F31h5ChN6wczETViPt", "distracted", "200921"));

        mockMvc.perform(MockMvcRequestBuilders.get("/band/{name}", "Disposable Teens"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
/*                .andExpect(content().json("{\n" +
                        "  \"id\": 807,\n" +
                        "  \"name\": \"Distracted\",\n" +
                        "  \"genre\": \"Pop-Rock\",\n" +
                        "  \"year\": 2009,\n" +
                        "  \"artist\": \"KSM\",\n" +
                        "  \"album\": \"Read Between the Lines\",\n" +
                        "  \"bpm\": \"129\",\n" +
                        "  \"spotifyId\": \"1v47F31h5ChN6wczETViPt\",\n" +
                        "  \"shortname\": \"distracted\",\n" +
                        "  \"duration\": \"200921\"\n" +
                        "}"));*/
    }


    @Test
    void findSongByGenre() throws Exception {
        mockMvc = standaloneSetup(musicController).build();

        // Assume that the service returns a list of bands in response to this request
       // when(musicService.findBandByGenre(anyString())).thenReturn(Collections.singletonList(new Band("MetalBand")));

        mockMvc.perform(MockMvcRequestBuilders.get("/song/{genre}", "Metal"))
                .andExpect(status().isOk());
    }


    @Test
    void addBand() throws Exception {
        mockMvc = standaloneSetup(musicController).build();

       // Band band = new Band("NewBand");

       // when(musicService.saveBand(any(Band.class))).thenReturn(band);

        mockMvc.perform(MockMvcRequestBuilders.post("/addband")
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addSong() throws Exception {
        mockMvc = standaloneSetup(musicController).build();

       // Song band = new Song(111, "Bad");

       // when(musicService.saveSong(any(Song.class))).thenReturn(song);

        mockMvc.perform(MockMvcRequestBuilders.post("/addSong")
                        .contentType(MediaType.APPLICATION_JSON));
    }

    // Add more test cases for other methods...

}
