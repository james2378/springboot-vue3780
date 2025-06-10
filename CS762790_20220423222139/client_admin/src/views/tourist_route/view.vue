<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','place_of_departure') || $check_field('add','place_of_departure') || $check_field('set','place_of_departure')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="出发地" prop="place_of_departure">
					<el-input id="place_of_departure" v-model="form['place_of_departure']" placeholder="请输入出发地"
							  v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','place_of_departure')) || (!form['tourist_route_id'] && $check_field('add','place_of_departure'))" :disabled="disabledObj['place_of_departure_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','place_of_departure')">{{form['place_of_departure']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','destination') || $check_field('add','destination') || $check_field('set','destination')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="目的地" prop="destination">
					<el-input id="destination" v-model="form['destination']" placeholder="请输入目的地"
							  v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','destination')) || (!form['tourist_route_id'] && $check_field('add','destination'))" :disabled="disabledObj['destination_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','destination')">{{form['destination']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','mode_of_transportation') || $check_field('add','mode_of_transportation') || $check_field('set','mode_of_transportation')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="交通方式" prop="mode_of_transportation">
					<el-select id="mode_of_transportation" v-model="form['mode_of_transportation']"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','mode_of_transportation')) || (!form['tourist_route_id'] && $check_field('add','mode_of_transportation'))">
						<el-option v-for="o in list_mode_of_transportation" :key="o['mode_of_transportation']" :label="o['mode_of_transportation']"
							:value="o['mode_of_transportation']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','mode_of_transportation')">{{form['mode_of_transportation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','tourism_type') || $check_field('add','tourism_type') || $check_field('set','tourism_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="旅游类型" prop="tourism_type">
					<el-select id="tourism_type" v-model="form['tourism_type']"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','tourism_type')) || (!form['tourist_route_id'] && $check_field('add','tourism_type'))">
						<el-option v-for="o in list_tourism_type" :key="o['tourism_type']" :label="o['tourism_type']"
							:value="o['tourism_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','tourism_type')">{{form['tourism_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','destination_scenery') || $check_field('add','destination_scenery') || $check_field('set','destination_scenery')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="终点风景" prop="destination_scenery">
					<el-upload :disabled="disabledObj['destination_scenery_isDisabled']" id="destination_scenery" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_destination_scenery"
						:show-file-list="false" v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','destination_scenery')) || (!form['tourist_route_id'] && $check_field('add','destination_scenery'))">
						<img v-if="form['destination_scenery']" :src="$fullUrl(form['destination_scenery'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','destination_scenery')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['destination_scenery'])" :preview-src-list="[$fullUrl(form['destination_scenery'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','travel_time') || $check_field('add','travel_time') || $check_field('set','travel_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="旅行时间" prop="travel_time">
					<el-input id="travel_time" v-model="form['travel_time']" placeholder="请输入旅行时间"
							  v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','travel_time')) || (!form['tourist_route_id'] && $check_field('add','travel_time'))" :disabled="disabledObj['travel_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','travel_time')">{{form['travel_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','total_cost') || $check_field('add','total_cost') || $check_field('set','total_cost')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="总体花费" prop="total_cost">
					<el-input id="total_cost" v-model="form['total_cost']" placeholder="请输入总体花费"
							  v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','total_cost')) || (!form['tourist_route_id'] && $check_field('add','total_cost'))" :disabled="disabledObj['total_cost_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','total_cost')">{{form['total_cost']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','travel_route') || $check_field('add','travel_route') || $check_field('set','travel_route')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="行程路线" prop="travel_route">
					<el-input type="textarea" id="travel_route" v-model="form['travel_route']" placeholder="请输入行程路线"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','travel_route')) || (!form['tourist_route_id'] && $check_field('add','travel_route'))" :disabled="disabledObj['travel_route_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','travel_route')">{{form['travel_route']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','travel_details') || $check_field('add','travel_details') || $check_field('set','travel_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="旅途详情" prop="travel_details">
					<quill-editor v-model.number="form['travel_details']"
						v-if="user_group === '管理员' || (form['tourist_route_id'] && $check_field('set','travel_details')) || (!form['tourist_route_id'] && $check_field('add','travel_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','travel_details')" v-html="form['travel_details']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "tourist_route_id",
				url_add: "~/api/tourist_route/add?",
				url_set: "~/api/tourist_route/set?",
				url_get_obj: "~/api/tourist_route/get_obj?",
				url_upload: "~/api/tourist_route/upload?",

				query: {
					"tourist_route_id": 0,
				},

				form: {
					"place_of_departure":'', // 出发地
					"destination":'', // 目的地
					"mode_of_transportation":'', // 交通方式
					"tourism_type":'', // 旅游类型
					"destination_scenery":'', // 终点风景
					"travel_time":'', // 旅行时间
					"total_cost":'', // 总体花费
					"travel_route":'', // 行程路线
					"travel_details":'', // 旅途详情
					"tourist_route_id": 0, // ID

				},
				disabledObj:{
					"place_of_departure_isDisabled": false,
					"destination_isDisabled": false,
					"mode_of_transportation_isDisabled": false,
					"tourism_type_isDisabled": false,
					"destination_scenery_isDisabled": false,
					"travel_time_isDisabled": false,
					"total_cost_isDisabled": false,
					"travel_route_isDisabled": false,
					"travel_details_isDisabled": false,
				},
				//交通方式选项列表
				list_mode_of_transportation: [],
				//旅游类型选项列表
				list_tourism_type: [],
			}
		},
		methods: {
			/**
			 * 获取交通方式列表
			 */
			async get_list_mode_of_transportation() {
				var json = await this.$get("~/api/mode_of_transportation/get_list?");
				if(json.result && json.result.list){
					this.list_mode_of_transportation = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 获取旅游类型列表
			 */
			async get_list_tourism_type() {
				var json = await this.$get("~/api/tourism_type/get_list?");
				if(json.result && json.result.list){
					this.list_tourism_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传终点风景
			 * @param {Object} param图片参数
			 */
			upload_destination_scenery(param){
				this.uploadFile(param.file, "destination_scenery");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/tourist_route/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/tourist_route/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_mode_of_transportation();
			this.get_list_tourism_type();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
