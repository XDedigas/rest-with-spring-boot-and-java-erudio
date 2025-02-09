package br.com.erudio.rest_with_spring_boot_and_java_erudio.controllers;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1.BookVO;
import br.com.erudio.rest_with_spring_boot_and_java_erudio.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.erudio.rest_with_spring_boot_and_java_erudio.util.MediaType.*;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {
    @Autowired
    private BookServices service;

    @GetMapping(produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @Operation(summary = "Finds all Book", description = "Finds all Book", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public List<BookVO> findAll() {
        return service.findAll();
    }

    @GetMapping(
            value="/{id}",
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @Operation(summary = "Finds a Book", description = "Finds a Book", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public BookVO findById(@PathVariable(value = "id")  long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML },
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @Operation(summary = "Adds a new Book", description = "Adds a new Book by passing in a JSON, XML or YML representation of the Book!", tags = {"Book"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public BookVO create(@RequestBody BookVO bookVO) { return service.create(bookVO); }

    @PutMapping(
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML },
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @Operation(summary = "Updates a Book", description = "Updates a Book by passing in a JSON, XML or YML representation of the Book!", tags = {"Book"}, responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public BookVO update(@RequestBody BookVO bookVO){
        return service.update(bookVO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Book", description = "Deletes a Book by passing in a JSON, XML or YML representation of the Book!", tags = {"Book"}, responses = {
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
