package cn.yt4j.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.yt4j.security.model.UserCache;
import cn.yt4j.security.util.JwtUtil;
import cn.yt4j.sys.dao.SysMenuDao;
import cn.yt4j.sys.dao.SysRoleDao;
import cn.yt4j.sys.dao.SysUserDao;
import cn.yt4j.sys.entity.SysMenu;
import cn.yt4j.sys.entity.SysUser;
import cn.yt4j.sys.entity.dto.UserDTO;
import cn.yt4j.sys.entity.vo.ActionEntitySet;
import cn.yt4j.sys.entity.vo.Permissions;
import cn.yt4j.sys.entity.vo.Role;
import cn.yt4j.sys.entity.vo.UserInfo;
import cn.yt4j.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户(SysUser)表服务实现类
 *
 * @author gyv12345@163.com
 * @since 2020-08-07 17:11:45
 */
@AllArgsConstructor
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

	private final PasswordEncoder encoder;

	private final JwtUtil jwtUtil;

	private final RedisTemplate<String, UserCache> redisTemplate;

	private final SysRoleDao sysRoleDao;

	private final SysMenuDao sysMenuDao;

	@Override
	public String login(UserDTO dto) {

		SysUser user = this.baseMapper
				.selectOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, dto.getUsername()));
		if (ObjectUtil.isNull(user)) {
			throw new BadCredentialsException("无此用户");
		}
		else {
			// 通过密码编码器比较密码
			if (encoder.matches(dto.getPassword(), user.getPassword())) {
				// 登录成功，创建token，我们需要在这里返回userDetail内容，包含权限信息,并将其放入redis，通过redis跨项目共享
				String token = jwtUtil.generateToken(user.getUsername());

				if (redisTemplate.hasKey(user.getUsername())) {
					redisTemplate.delete(user.getUsername());
				}

				UserCache cache = new UserCache();
				cache.setId(user.getId());
				BeanUtils.copyProperties(user, cache);
				cache.setRoles(sysRoleDao.listByUserId(user.getId()));
				cache.setMenus(sysMenuDao.listByUserId(user.getId()));

				redisTemplate.opsForValue().set(user.getUsername(), cache, 30L, TimeUnit.DAYS);

				return token;
			}
			else {
				throw new BadCredentialsException("用户或密码错误");
			}
		}
	}

	@Override
	public UserInfo getInfo(Long id) {
		UserInfo userInfo = new UserInfo();
		Role role = new Role();
		List<Permissions> permissionsList = new ArrayList<>();

		SysUser user = this.baseMapper.selectById(id);
		List<SysMenu> menus = this.sysMenuDao.listMenuByUserId(id);
		menus.forEach(sysMenu -> {
			Permissions permissions = new Permissions();
			permissions.setPermissionName(sysMenu.getPermission());
			permissions.setActionEntitySet(this.sysMenuDao
					.selectList(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, sysMenu.getId())).stream()
					.map(menu -> {
						ActionEntitySet set = new ActionEntitySet();
						set.setAction(menu.getPermission());
						set.setDescribe(menu.getTitle());
						set.setDefaultCheck(false);
						return set;
					}).collect(Collectors.toList()));
			permissionsList.add(permissions);
		});
		userInfo.setName(user.getNickName());
		userInfo.setAvatar(user.getAvatar());
		role.setPermissions(permissionsList);

		userInfo.setRole(role);
		return userInfo;
	}

}