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
import com.disk91.etl.service.ExitService;
import com.disk91.etl.service.PrometeusService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@Tag( name = "admin api", description = "admin api" )
@CrossOrigin
@RequestMapping(value = "/internal/3.0")
@RestController
public class InternalApi {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExitService exitService;

    @Autowired
    protected PrometeusService prometeusService;

    @Operation(summary = "Exit the application - this API is not exposed",
            description = "Request the backend to stop processing after terminating current work.",
            responses = {
                @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/exit",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> requestStopApplication(
            HttpServletRequest request
    ) {
        log.info("Request to stop the application");
        exitService.onCallExit();
        return new ResponseEntity<>(ActionResult.SUCESS(), HttpStatus.OK);
    }

    @Operation(summary = "Pause the application - this API is not exposed",
            description = "Request the backend to pause processing after terminating current work.",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/pause",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> requestPauseApplication(
            HttpServletRequest request
    ) {
        log.info("Request to pause the application");
        exitService.onCallPause();
        return new ResponseEntity<>(ActionResult.SUCESS(), HttpStatus.OK);
    }

    @Operation(summary = "Pause state of the application - this API is not exposed",
            description = "return 200 when the pause has been completed and 202 when not yet",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class))),
                    @ApiResponse(responseCode = "202", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/pause/status",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> requestPauseStatus(
            HttpServletRequest request
    ) {
        if ( exitService.isInPause() ) {
            return new ResponseEntity<>(ActionResult.SUCESS(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ActionResult.SUCESS(), HttpStatus.ACCEPTED);
        }
    }


    @Operation(summary = "Resume the application - this API is not exposed",
            description = "Request the backend to resume processing ",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/resume",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> requestResumeApplication(
            HttpServletRequest request
    ) {
        log.info("Request to resume the application");
        exitService.onCallResume();
        return new ResponseEntity<>(ActionResult.SUCESS(), HttpStatus.OK);
    }


    @Operation(summary = "Healthcheck",
            description = "Just to make sure the api is up & running.",
            responses = {
                    @ApiResponse(responseCode = "200", description= "Done", content = @Content(schema = @Schema(implementation = ActionResult.class)))
            }
    )
    @RequestMapping(value="/health",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method= RequestMethod.GET)
    public ResponseEntity<?> requestApplicationHealth(
            HttpServletRequest request
    ) {
        // long startMs= Now.NowUtcMs();
        // ...
        // prometeusService.addApiTotalTimeMs(startMs);
        return new ResponseEntity<>(ActionResult.SUCESS(), HttpStatus.OK);
    }

}

