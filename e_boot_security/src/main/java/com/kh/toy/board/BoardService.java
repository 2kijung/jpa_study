package com.kh.toy.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.HandlableException;
import com.kh.toy.common.util.file.FileInfo;
import com.kh.toy.common.util.file.FileUtil;
import com.kh.toy.common.util.paging.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
		return boardRepository.findById(bdIdx)
							  .orElseThrow(() -> new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR));
	}

	public Map<String, Object> findBoardsByPage(int page) {
		//PageRequest는 페이지를 0부터 시작한다... 리얼 개발자 새끼들...
		int cntPerPage = 5;
		
		Page<Board> boardList = boardRepository
								.findAll(PageRequest.of(page-1, cntPerPage, Direction.DESC, "bdIdx"));
		
		Paging paging = Paging.builder()
					.url("/board/board-list")
					.blockCnt(5)
					.cntPerPage(cntPerPage)
					.currentPage(page)
					.total((int)boardRepository.count())
					.build();
		
		
		return Map.of("boardList", boardList, "paging", paging);
	}

	@Transactional
	public void modifyBoard(Board board, List<MultipartFile> files, List<Long> removeFlIdx) {
		
		Board boardEntity = boardRepository.findById(board.getBdIdx())
											.orElseThrow(() -> new HandlableException(ErrorCode.REDIRECT));
		
		FileUtil util = new FileUtil();
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		boardEntity.getFileInfos().removeIf(e -> {
			if(removeFlIdx.contains(e.getFlIdx())) {
				util.deleteFile(e.getDownloadPath());
				return true;
			}
			return false;
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
