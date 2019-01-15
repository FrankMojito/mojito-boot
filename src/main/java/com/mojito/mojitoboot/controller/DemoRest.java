package com.mojito.mojitoboot.controller;

import com.mojito.mojitoboot.common.domain.ConfigBean;
import com.mojito.mojitoboot.common.domain.User;
import com.mojito.mojitoboot.utils.CSVUtil;
import com.mojito.mojitoboot.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mojito
 * @desc springboot demo
 */

/**
 * RestController相当于Controller+repsoneBody
 */
@RestController
@Slf4j
public class DemoRest {

	@Autowired
	private ConfigBean configBean;

	@Autowired
	private RedisUtil redisUtil;


	/**
	 * @GetMapping = @RequestMapping(method = RequestMethod.GET)
	 * @PostMapping = @RequestMapping(method = RequestMethod.POST)
	 * @PutMapping = @RequestMapping(method = RequestMethod.PUT)
	 * @DeleteMapping = @RequestMapping(method = RequestMethod.DELETE
	 */
	@RequestMapping("/")
	public String index() {
		System.out.println(configBean.getName());
		User user= new User(2,"按时大大aaaaaaaa我我我的");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("name", user.getName());
		redisUtil.add(user.getName(), user);
		System.out.println("asasassa");
		Set<Object> setMembers = redisUtil.setMembers(user.getName());
		setMembers.forEach(item -> {
			System.out.println(item);
			User user1 = (User) item;
			System.out.println(user1.getName());
		});
		
		return configBean.getName() + "Hello Spring Boot" + configBean.getDesc();
		
	}
	
	/**
	 * 通过@PathVariable接收参数
	 */
	@GetMapping(value = "/test1/{id}/{name}")
	public Map<String, Object> getMap(@PathVariable("id") String id, @PathVariable("name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("xkkkk是酷酷酷酷酷酷xx","xxxxxxaaaaaaaaaaaaaaaaaaaa");
		return map;
	}

	/**
	 * get请求的用法
	 *
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/get1", method = RequestMethod.GET)
	public Map<String, Object> getController(@RequestParam(value = "id") String id,
			@RequestParam(value = "name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		return map;
	}

	@GetMapping(value = "get2")
	public Map<String, Object> getController2(@RequestParam(value = "id") String id,
			@RequestParam(value = "name") String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		return map;
	}

	/**
	 * post请求的用法
	 */
	@RequestMapping(value = "/post1", method = RequestMethod.POST)
	public Map<String, Object> postController(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("name", user.getName());

		return map;
	}

	@PostMapping(value = "post2")
	public Map<String, Object> postController2(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("name", user.getName());
		return map;
	}

	/**
	 * put请求的用法
	 */
	@RequestMapping(value = "/put1", method = RequestMethod.PUT)
	public Map<String, Object> putController(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("name", user.getName());
		return map;
	}

	@PutMapping(value = "put2")
	public Map<String, Object> putController2(@RequestBody User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", user.getId());
		map.put("name", user.getName());
		return map;
	}

	/**
	 * delete请求的用法
	 */
	@RequestMapping(value = "/delete1", method = RequestMethod.DELETE)
	public Map<String, Object> deleteController(@RequestParam(value = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return map;
	}

	@DeleteMapping(value = "delete2")
	public Map<String, Object> deleteController2(@RequestParam(value = "id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return map;
	}

	/**
	 * 文件上传 .csv文件 栗子
	 *
	 * @param multipartFile
	 */
	@PostMapping("/upload")
	public List<User> singleFileUpload(@RequestParam("file") MultipartFile multipartFile) {

		List<User> list = null;
		
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(multipartFile.getInputStream(), "utf-8");
			list = CSVUtil.importCsv(isr);
		} catch (IOException e) {
			log.error(e.toString());
		} catch (Exception e) {
			log.error(e.toString());
		}
		HashMap<Object, Object> hashMap = new HashMap<>();
		list.forEach(item->{
			hashMap.put(item.getName(), item);
		});
		
		
		redisUtil.hmSet("表格", hashMap);
		return list;

	}

	/**
	 * java导出cvs文件 数据集
	 * <p>
	 * 导出路径
	 *
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/exportCsv")
	public void downLoadFile(HttpServletResponse response) {
		
//		
//		OutputStream out = null;
//		try {
//			response.reset();
//			response.setContentType("application/octet-stream; charset=utf-8");
//			response.setHeader("Content-Disposition", "attachment; filename=" + "a.csv");
//			out = response.getOutputStream();
//
//			CsvWriter writer = new CsvWriter(out, ',', Charset.forName("GBK"));
//			users.forEach(item -> {
//				String[] a = new String[] { item.getId().toString(), item.getName() };
//				try {
//					writer.writeRecord(a);
//				} catch (IOException e) {
//					log.error(e.toString());
//				}
//			});
//
//			writer.close();
//			out.flush();
//		} catch (IOException e) {
//			log.error(e.toString());
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					log.error(e.toString());
//				}
//			}
//		}
	}

	/**
	 * @Description 下载文件更优雅的做法
	 * @author
	 */
	public ResponseEntity<byte[]> download(String fileName, File file) throws IOException {
		String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}

}
