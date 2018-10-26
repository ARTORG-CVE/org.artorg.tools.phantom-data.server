package org.artorg.tools.phantomData.server.controller;

import org.artorg.tools.phantomData.server.model.DbFile;
import org.artorg.tools.phantomData.server.service.iService.IfileService;
import org.artorg.tools.phantomData.server.specification.ControllerSpec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("files")
public class FileController extends ControllerSpec<DbFile, IfileService<DbFile>> {

	@GetMapping("get-by-name/{name}")
	public ResponseEntity<DbFile> getByName(@PathVariable("name") String name) {
		DbFile f = service.getByName(name);
		return new ResponseEntity<DbFile>(f, HttpStatus.OK);
	}

}
