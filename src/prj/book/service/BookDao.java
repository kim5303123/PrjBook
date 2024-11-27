/**
 * 
 */
package prj.book;

import java.util.List;

/**
 * @Author : TeamPrjBook
 * @Date   : 2024. 11. 27.
 * 
 * DAO(Data Access Object)는 DB를 사용해 데이터를 조화하거나 
 * 조작하는 기능을 전담하도록 만든 오브젝트
 */
public interface BookDao {

//	전체 작가 목록
	public List<BookVo> getList();
	
//	검색
	public List<BookVo> search(String keyword);
	
//	개별 객체 반환
	public BookVo get(Long id);
	
//	insert
	public boolean insert(BookVo vo);
	
//	update
	public boolean update(BookVo vo);
	
//	delete
	public boolean delete(Long id);
	
}
