<template>
	<div class="diy_details page_tourist_route" id="tourist_route_details">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div class='col'>
						<div class='card_tourist_route'>
							<div_tourist_route :obj="obj"></div_tourist_route>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 地图 -->
		<div class="warp">
			<div class="container" style="margin-top:8px;">
				<div class="row">
					<div class="col">
						<template>
							<div>
								<iframe style="border: none" :width="searchTableHeight+100" :height="searchTableHeight"
									v-bind:src="reportUrl"></iframe>
							</div>
						</template>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import div_tourist_route from "@/components/diy/div_tourist_route.vue";
	import mixin from "@/mixins/page.js";
	export default {
		mixins:[mixin],
		components:{
			div_tourist_route
		},
		data(){
			return{
				url_get_obj: "~/api/tourist_route/get_obj?",

				field: "tourist_route_id",

				obj: {
				},

				query: {
					"place_of_departure": "",
					"destination": "",
					"mode_of_transportation": "",
					"tourism_type": "",
					"destination_scenery": "",
					"travel_time": "",
					"total_cost": "",
					"travel_route": "",
					"travel_details": "",
					"tourist_route_id":0,
				},
				// 点赞
				praise: 0,
				// 点赞状态
				state_praise: false,
				// 收藏状态
				state_collect: false,
				reportUrl: "https://map.baidu.com/@12683619.778731756,2559833.254994969,18.04z",
				searchTableHeight: 0,
				searchTableWidth: 0,
				
			}
		},
		methods:{
			/**
			 * 添加访问量
			 */
			add_hits(obj) {
				obj["hits"] = obj["hits"] + 1;
				var hits = obj["hits"];
				this.$post('~/api/tourist_route/set?tourist_route_id=' + obj["tourist_route_id"], {
					hits
				}, (res) => {
					if(res.result){
						console.log("添加访问量状态：" ,res.result);
						var body = {
							source_table: "tourist_route",
							source_field: "tourist_route_id",
							source_id: this.obj.tourist_route_id,
							user_id: this.$store.state.user.user_id,
						};
						this.$post("~/api/hits/add?", body, (res) => {
							console.log(res);
						});
					}
					else if(res.error){
						console.error(res.error);
					}
				});
			},
			get_obj_after(json) {
				// 判断是否获取到数据
				if (json && json.result && json.result.obj) {
					console.log(json.result.obj)
					var obj = json.result.obj;
					this.obj = obj
					// 增加点击数
					this.add_hits(obj);
					// 初始化收藏状态
					this.get_collect(obj);
				}
			},
			/**
			 * 初始化收藏状态
			 * @param {Object} obj
			 */
			get_collect(obj) {
				var user_id = this.user.user_id;

				var query = {
					source_table: "tourist_route",
					source_field: "tourist_route_id",
					source_id: obj["tourist_route_id"],
					user_id
				};

				this.$get('~/api/collect/count?', query, (res) => {
					console.log(res);
					if (res.result || res.result === 0) {
						var bl = res.result ? true : false;
						this.state_collect = bl;
						console.log("收藏状态：" + bl);
					}
					else if(res.error) {
						console.error(res.error);
					}
				});
			},
		 /****
			 * 分享点击
			 * 
			 *  */ 
			toShare(e){
				window.open(e);
			},
			/**
			 * 改变收藏状态
			 */
			change_collect() {
				var user_id = this.user.user_id;

				var query = {
					source_table: "tourist_route",
					source_field: "tourist_route_id",
					source_id: this.obj["tourist_route_id"],
					user_id
				};

				// 收藏状态
				if (this.state_collect) {
					this.state_collect = false;
					this.$get('~/api/collect/del?', query, (res) => {
						if(res.result){
							this.$toast("取消收藏");
						}
						else if (res.error){
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				} else {
					this.state_collect = true;
					query.title = this.obj.destination
					query.img = this.obj.destination_scenery
					this.$post('~/api/collect/add?', query, (res) => {
						if (res.result) {
							this.$toast("收藏成功");
						}
						else if (res.error) {
							this.$toast(res.error.message);
							console.error(res.error);
						}
					});
				};
			},
			widthHeight() {
				this.searchTableHeight = window.innerHeight - 146;
				this.searchTableWidth = window.innerWidth - 280;
			},
		},
		
		mounted() {
			window.onresize = () => {
				this.widthHeight(); // 自适应高宽度
			};
			this.$nextTick(function() {
				this.widthHeight();
			});
			
		},
		
	}
</script>

<style>
.qecode {
  display: flex;
  align-content: center;
  width: 270px;
  background: white;
  position: absolute;
  left: 20%;
  top: 20px;
  z-index: 999;
}
.qecodeImg {
  display: flex;
  flex-direction: column;
  align-content: center;
  text-align: center;
  padding-top: 10px;
  margin: 0 auto;
}
.btn_share{
	display: inline-block;
	position: relative;
	top: 15px;
	background: rgb(240, 238, 238);
	border: 1px solid #ccc;
	height: 38px;
	margin-left: 10px;
	padding: 3px 10px;
	cursor: pointer;
	overflow: hidden;
	border-radius: 5px;
}
.btn_share_icon{
	width: 30px;
	height: 28px;
	float: left;
	margin-right: 10px;
}
.share_box{
	display: none;
	float: left;
}
.share_box img{
	width: 30px;
	height: 28px;
	margin-right: 10px;
}
.btn_share:hover .share_box{
	display: block;
}
</style>
