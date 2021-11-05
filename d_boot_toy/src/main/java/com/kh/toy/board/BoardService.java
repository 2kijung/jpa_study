package com.kh.toy.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.HandlableException;
import com.kh.toy.common.util.file.FileInfo;
import com.kh.toy.common.util.file.FileUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService{
	
	private final BoardRepository boardRepository;
	
	@Transactional
	public void persistBoard(List<MultipartFile> multiparts, Board board) {
		
		FileUtil util = new FileUtil();
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		for (MultipartFile multipartFile : multiparts) {
			if(!multipartFile.isEmpty()) {
				fileInfos.add(util.fileUpload(multipartFile));
			}
		}
		board.setFileInfos(fileInfos);
		boardRepository.save(board);
		
	}

	public Board findBoardById(Long bdIdx) {
		return boardRepository.findById(bdIdx).orElseThrow(()->new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR));
	}

	public List<Board> findBoardsByPage(int page) {
		Page<Board> boardList = boardRepository.findAll(PageRequest.of(page-1, 5,Direction.ASC,"bdIdx"));
		return boardList.getContent();
	}
	
	

}
