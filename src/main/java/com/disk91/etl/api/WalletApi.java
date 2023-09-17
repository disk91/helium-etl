package com.disk91.etl.api;

import com.disk91.etl.api.interfaces.ActionResult;
import com.disk91.etl.api.interfaces.HotspotData;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.service.HotspotCache;
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

@Tag( name = "wallet api", description = "wallet api" )
@CrossOrigin
@RequestMapping(value = "/wallet/3.0")
@RestController
public class WalletApi {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HotspotCache hotspotCache;

    @Operation(summary = "Get Wallet hotspots",
        description = "Get wallet hotspots",
        responses = {
            @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class))),
            @ApiResponse(responseCode = "204", description= "No content", content = @Content(schema = @Schema(implementation = ActionResult.class)))
        }
    )
    @RequestMapping(value="/{walletId}/",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method= RequestMethod.GET)
    public ResponseEntity<?> getHotspotsList(
        HttpServletRequest request,
        @Parameter(required = true, name = "walletId", description = "Base58 solana wallet Id")
        @PathVariable String walletId
    ) {
      //  try {

            hotspotCache.getAndUpdateHotspotsPerOwner(walletId);

            return new ResponseEntity<>(ActionResult.NODATA(), HttpStatus.OK);
      //  } catch (ITNotFoundException x) {
      //      return new ResponseEntity<>(ActionResult.NODATA(), HttpStatus.NO_CONTENT);
      //  }
    }

}
