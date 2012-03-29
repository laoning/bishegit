<?php
	function keti_callback_page(){
		$render_array=array(
			'#markup'=>'',
		);
		$render_array['#markup'].=drupal_render(@ drupal_get_form('keti_form'));
		return $render_array;
	}


	
	/**
	 *选题表
	*/
	function keti_form(){
		drupal_add_css(drupal_get_path('module','keti').'/keti.css');
		$form['biaoti']=array('#markup'=>'<table class="keti-table">'.t('<tr><th></th><th>课题名称</th><th>指导教师</th></tr>'));
		$result=db_query("SELECT uid, title FROM {node} WHERE type=:type",array(':type'=>'fbkt',));

		$number=$result->rowCount();
		
		$i=0;
		/**while($result->fetchAssoc()){	

		$form['ktmc'][$i]=array(
			'#type'=>'checkbox',
			'#title'=>$result->fetchField(0).''.$result->fetchField(1),
			
		);
		$i++;
		}
		*/
			
		foreach ($result as $record){
		
		$re='';	
		$re1='http://localhost/xdrupal12/fbkt';
		//$form['ds'][$i]=array('#markup'=>'<tr>');
		foreach($record as $rec) {
		$re.='<td>'.$rec.'</td>';
		$re1.='/'.$rec;
		}
		$re2='<a href=>'.$re.'</a>';
		//$form['ds']=array('#markup'=>'<td>');
		$form['ktmc'][$i]=array(
			'#prefix'=>'<tr><td>',
			'#type'=>'checkbox',
			
			'#title'=>$re2,
			'#suffix'=>'</td></tr>',
		);
		//$form['de'][$i]=array('#markup'=>'</tr>');
		
		$i++;
		}
		$form['jw']=array('#markup'=>'</table>');
		foreach ($result as $record){
		
		
		//$re1='/fbkt';
		//$form['ds'][$i]=array('#markup'=>'<tr>');
		foreach($record as $rec) {
	
		$re1.='/'.$rec;
		}}
		//$url='/fbkt'.$re1;
		$form['jx']=array('#markup'=>'<a href="'.$re1.'">查看</a>');
		return $form;
	}
	/**
	 *确认选题
	*/
	function keti_confirm_callback_page(){
		drupal_add_css(drupal_get_path('module','keti').'/keti.css');
		$form['biaoti']=array('#markup'=>'<p>'.t('  课题名称                  指导教师').'</p>');
		return $form;
	}

	
