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

import com.disk91.etl.api.interfaces.ActionResult;
import com.disk91.etl.api.interfaces.HotspotData;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.service.ExitService;
import com.disk91.etl.service.HotspotCache;
import com.disk91.etl.service.PrometeusService;
import fr.ingeniousthings.tools.ITNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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


}

