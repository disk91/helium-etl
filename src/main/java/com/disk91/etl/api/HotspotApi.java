/*
 * Copyright (c) - Paul Pinault (aka disk91) - 2020.
 *
 *    Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 *    and associated documentation files (the "Software"), to deal in the Software without restriction,
 *    including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *    sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *
 *    The above copyright notice and this permission notice shall be included in all copies or
 *    substantial portions of the Software.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *    OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *    WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
 *    IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.disk91.etl.api;

import com.disk91.etl.api.interfaces.*;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.Reward;
import com.disk91.etl.service.*;
import fr.ingeniousthings.tools.ITNotFoundException;
import fr.ingeniousthings.tools.ITParseException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag( name = "hotspot api", description = "hotspot api" )
@CrossOrigin
@RequestMapping(value = "/hotspot/3.0")
@RestController
public class HotspotApi {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HotspotCache hotspotCache;

    @Operation(summary = "Get Hotspot details",
            description = "Get Hotspot radio & reward data",
            responses = {
                @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = HotspotData.class))),
                @ApiResponse(responseCode = "204", description= "No content", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/{hotspotId}/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotDetails(
            HttpServletRequest request,
            @Parameter(required = true, name = "hotspotId", description = "Base58 hotspot public key encoded")
            @PathVariable String hotspotId
    ) {
        try {
            Hotspot hs = hotspotCache.getOneHotspot(hotspotId);
            HotspotData r = new HotspotData();
            r.initFrom(hs);
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (ITNotFoundException x) {
            return new ResponseEntity<>(ActionResult.NODATA(), HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Get Hotspot details",
            description = "Get Hotspot radio & reward data",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = HotspotPosition.class))),
                    @ApiResponse(responseCode = "204", description= "No content", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/{hotspotId}/pos",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotPosition(
            HttpServletRequest request,
            @Parameter(required = true, name = "hotspotId", description = "Base58 hotspot public key encoded")
            @PathVariable String hotspotId
    ) {
        try {
            Hotspot hs = hotspotCache.getOneHotspot(hotspotId);
            HotspotPosition r = new HotspotPosition();
            r.initFrom(hs);
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (ITNotFoundException x) {
            return new ResponseEntity<>(ActionResult.NODATA(), HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Find Hotspot by animal name",
        description = "Search hotspot with animal name",
        responses = {
            @ApiResponse(responseCode = "200", description= "Done",
                content = @Content(array = @ArraySchema(schema = @Schema( implementation = HotspotIdent.class))))
        }
    )
    @RequestMapping(value="/search/{animal}/",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotByAnimal(
        HttpServletRequest request,
        @Parameter(required = true, name = "animal", description = "animal name with - between words")
        @PathVariable String animal
    ) {
         List<HotspotIdent> r = hotspotCache.getHotspotsByAnimal(animal);
         return new ResponseEntity<>(r, HttpStatus.OK);
    }


    @Operation(summary = "Find Hotspot by geography - max 200",
        description = "Search hotspot by geography - max 200",
        responses = {
            @ApiResponse(responseCode = "200", description= "Done",
                content = @Content(array = @ArraySchema(schema = @Schema( implementation = HotspotIdent.class))))
        }
    )
    @RequestMapping(value="/search/{latN}/{latS}/{lonW}/{lonE}/",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotByPosition(
        HttpServletRequest request,
        @Parameter(required = true, name = "latN", description = "latitude north")
        @PathVariable double latN,
        @Parameter(required = true, name = "latS", description = "latitude south")
        @PathVariable double latS,
        @Parameter(required = true, name = "lonW", description = "longitude west")
        @PathVariable double lonW,
        @Parameter(required = true, name = "lonE", description = "longitude est")
        @PathVariable double lonE
    ) {
        List<HotspotIdent> r = hotspotCache.getHotspotsByGeo(latN, lonW,latS, lonE);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }


    @Autowired
    protected RewardService rewardService;

    @Operation(summary = "Get Hotspot rewards",
            description = "Get Hotspot rewards data",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done",
                                    content = @Content(array = @ArraySchema(schema = @Schema( implementation = Reward.class)))),
                    @ApiResponse(responseCode = "400", description= "Bad Request", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/rewards/{hotspotId}/{from}/{to}/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotRewards(
            HttpServletRequest request,
            @Parameter(required = true, name = "hotspotId", description = "Base58 hotspot public key encoded")
            @PathVariable String hotspotId,
            @Parameter(required = true, name = "from", description = "from date UTC Ms")
            @PathVariable long from,
            @Parameter(required = true, name = "to", description = "to date UTC Ms")
            @PathVariable long to
    ) {
        try {
            List<Reward> r = rewardService.getHotspotRewards(hotspotId,from,to);
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (ITParseException x) {
            return new ResponseEntity<>(ActionResult.BADREQUEST(), HttpStatus.BAD_REQUEST);
        }
    }


    @Autowired
    protected MiscService miscService;

    @Operation(summary = "Get stat about Hotspot data loading",
            description = "Get Hotspot data loading",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = HotspotStat.class))),
                    @ApiResponse(responseCode = "204", description= "No content", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/stat/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotStat(
            HttpServletRequest request
    ) {
        try {
            return new ResponseEntity<>(miscService.getStats(), HttpStatus.OK);
        } catch (Exception x) {
            return new ResponseEntity<>(ActionResult.NODATA(), HttpStatus.NO_CONTENT);
        }
    }



}

