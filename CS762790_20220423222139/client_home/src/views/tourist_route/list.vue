<template>
	<div class="diy_list page_tourist_route" id="tourist_route_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">旅游路线列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">							
							<span class="diy_list_search_title">关键字搜索：</span>
							<b-form-input size="sm" class="mr-sm-2" placeholder="出发地搜索" v-model="query['place_of_departure']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="目的地搜索" v-model="query['destination']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="交通方式搜索" v-model="query['mode_of_transportation']" />
							<b-form-input size="sm" class="mr-sm-2" placeholder="旅游类型搜索" v-model="query['tourism_type']" />
							<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
								<b-dropdown text="交通方式" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','mode_of_transportation')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_mode_of_transportation" :key="i" @click="filter_set(o['mode_of_transportation'],'mode_of_transportation')" >
												{{ o['mode_of_transportation'] }}
										</b-dropdown-item>
								</b-dropdown>
								<b-dropdown text="旅游类型" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','tourism_type')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_tourism_type" :key="i" @click="filter_set(o['tourism_type'],'tourism_type')" >
												{{ o['tourism_type'] }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>
				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_tourist_route :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_tourist_route from "@/components/diy/list_tourist_route.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_tourist_route
		},
		data() {
			return {
				url_get_list: "~/api/tourist_route/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
					"place_of_departure": "", // 出发地
					"destination": "", // 目的地
					"mode_of_transportation": "", // 交通方式
					"tourism_type": "", // 旅游类型
				},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "create_time desc",
					},
					{
						name: "创建时间从低到高",
						value: "create_time asc",
					},
					{
						name: "更新时间从高到低",
						value: "update_time desc",
					},
					{
						name: "更新时间从低到高",
						value: "update_time asc",
					},
					{
						name: "出发地正序",
						value: "place_of_departure asc",
					},
					{
						name: "出发地倒序",
						value: "place_of_departure desc",
					},
					{
						name: "目的地正序",
						value: "destination asc",
					},
					{
						name: "目的地倒序",
						value: "destination desc",
					},
					{
						name: "交通方式正序",
						value: "mode_of_transportation asc",
					},
					{
						name: "交通方式倒序",
						value: "mode_of_transportation desc",
					},
					{
						name: "旅游类型正序",
						value: "tourism_type asc",
					},
					{
						name: "旅游类型倒序",
						value: "tourism_type desc",
					},
				],
				// 交通方式列表
				"list_mode_of_transportation": [],
				// 旅游类型列表
				"list_tourism_type": [],
			}
		},
		methods: {
      get_list_before(param) {
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},
			/**
			 * 获取交通方式列表
			 */
			async get_list_mode_of_transportation() {
				var json = await this.$get("~/api/mode_of_transportation/get_list?");
				if (json.result) {
					this.list_mode_of_transportation = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},
			/**
			 * 获取旅游类型列表
			 */
			async get_list_tourism_type() {
				var json = await this.$get("~/api/tourism_type/get_list?");
				if (json.result) {
					this.list_tourism_type = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},

			/**
			 * 筛选
			 */
			filter_mode_of_transportation(o) {
				if (o == "全部") {
					this.query["mode_of_transportation"] = "";
				} else {
					this.query["mode_of_transportation"] = o;
				}
				this.search();
			},
			filter_tourism_type(o) {
				if (o == "全部") {
					this.query["tourism_type"] = "";
				} else {
					this.query["tourism_type"] = o;
				}
				this.search();
			},

			/**
			 * 重置
			 */
			reset() {
				this.query.place_of_departure = ""
				this.query.destination = ""
				this.query.mode_of_transportation = ""
				this.query.tourism_type = ""
				this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },
		},
		computed: {
		},
		created() {
			/**
			 * 获取交通方式列表
			 */
			this.get_list_mode_of_transportation();
			/**
			 * 获取旅游类型列表
			 */
			this.get_list_tourism_type();
		}
	}
</script>

<style>
</style>
