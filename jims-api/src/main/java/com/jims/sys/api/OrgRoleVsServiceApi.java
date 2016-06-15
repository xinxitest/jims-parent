package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface OrgRoleVsServiceApi {

    /**
     * 根据角色权限id获取
     * @param id
     * @return
     */
    public OrgRoleVsService get(String id);

    /**
     *  获取角色服务权限列表
     * @param orgRoleVsService
     * @return
     */
    public List<OrgRoleVsService> findList(OrgRoleVsService orgRoleVsService);

    /**
     *  获取角色权限列表
     * @param page
     * @param orgRoleVsService
     * @return
     */
    public Page<OrgRoleVsService> findPage(Page<OrgRoleVsService> page, OrgRoleVsService orgRoleVsService);

    /**
     *  保存角色角色权限
     * @param orgRoleVsService
     * @return
     */
    public String OrgRoleVsServiceSave(List<OrgRoleVsService> orgRoleVsService);

    /**
     * 删除角色权限
     * @param orgRoleVsService
     * @return
     */
    public String delete(OrgRoleVsService orgRoleVsService);

    /**
     * 查询所有角色权限
     * @return
     */
    public List<OrgRoleVsService> findAll();

    /**
     * 根据主键进行查询
     * @param id
     * @return
     */
    public List<RoleServiceMenu> find(String id);

    /**
     * 根据角色id进行查询
     * @param roleid
     * @return
     */
    public List<OrgRoleVsService> findRole(String roleid);
}
