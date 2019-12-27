package org.jvsun.dao;

import java.util.List;

import org.jvsun.pojo.PhotoPOJO;

/**
 *照片接口
 */
public interface PhotoDAO {
	/**
	 * 新增照片
	 * @param pojo
	 * @return
	 */
	public boolean doIns(PhotoPOJO pojo);
	/**
	 * 根据id删除照片
	 * @param ContractId
	 * @return
	 */
	public boolean doDel(int PhotoId);
	/**
	 * 更新照片
	 * @param pojo
	 * @return
	 */
	public boolean doUpd(PhotoPOJO pojo);
	/**
	 * 根据合同id查找照片
	 * @param ContractId
	 * @return
	 */
	public List<PhotoPOJO> findAllByContractId(int ContractId);
	/**
	 * 根据照片id查询照片
	 * @param PhotoId
	 * @return
	 */
	public List<PhotoPOJO> findAllByPhotoId(int PhotoId);
	
}
