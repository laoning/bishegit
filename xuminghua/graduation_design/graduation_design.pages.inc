<?php
	function graduation_design_home_page(){
		global $user;
		$render_array=array();
		$render_array['#markup']=t('我的主页面内容');
		return $render_array;
	}
