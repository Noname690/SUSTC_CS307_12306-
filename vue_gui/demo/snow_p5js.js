// 张嘉兮 11812318 2020-2-27

/*
 * 绘制不同速度，不同大小的下落的雪花们
 * 目前只有垂直下落，下一步拓展横向移动
 */

//雪花的最大/最小直径
let maxDiameter = 55;
let minDiameter = 15;
//雪花最大旋转角速率
let maxAngularSpeed = 2*Math.PI/180;
//雪花的最大/最小下落速率
let maxFallSpeed = 4.0;
let minFallSpeed = 0.9;
//雪花的最大/最小透明度
let maxAlpha = 150;
let minAlpha = 30;
//最大共存雪花数量
let maxSnowCount = 50;
//存放现存雪花
let snows = [];

/**
 * 准备工作
 */
function setup() {
  //fullScreen(); //全屏
  createCanvas(600, 900);
  //不要边框
  noStroke(); 
  //获取背景图片，以下为版权信息
  // Photo on <a href="https://visualhunt.com/re6/7f9b17a0">VisualHunt.com</a>
}

/**
 * 循环方法
 */
function draw() {
  //刷新背景
  //background(125);
  background(255,255,255)
  //现存雪花数不足时随机决定生成雪花
  if (frameCount%3==0&&snows.length<maxSnowCount&&random()<0.3) {
    snows.push(new Snow());
  }
  fill(0,0,0);
  //更新各种位置数值
  for (let i in snows) {
    if(snows[i].update()){
      snows.splice(i,1);
    }
  }
  //绘制所有雪花
  for (let i in snows) {
    snows[i].redraw(); //请问老师，实例方法的这种命名重复应该没有影响吧？
  }
}

class Snow {


  constructor() {
    //在最大直径范围内随机
    this.d = random(minDiameter,maxDiameter);
    //生成时刚好完全在屏幕外
    this.center = new Vertex({x:random(width), y:-0.5*this.d,snow:this});
    //在最大角速率范围内随机出[-max,max]的角速率
    this.angularSpeed = random(-maxAngularSpeed,maxAngularSpeed);
    //在最大下落速率范围内随机
    this.fallSpeed = random(minFallSpeed,maxFallSpeed);
    //透明度在[30,130]内随机
    this.alpha = random(minAlpha,maxAlpha);
    //生成分支们
    this.branches = this.createBranches();
  }


  /**
   * 更新整片雪花
   */
  update() {
    this.center.fall();
    for (let i in this.branches) {
      this.branches[i].update();
    }
    if (this.center.y-0.5*this.d>height) {
      return true;
    }
    return false;
  }

  /**
   * 绘制整片雪花
   */
  redraw() {
    //中心圆的颜色
    fill(255, 255, 255, this.alpha);
    //绘制中心圆
    ellipse(this.center.x, this.center.y, 0.3*this.d, 0.3*this.d); 
    //绘制分支们
    for (let i in this.branches) {
      this.branches[i].redraw();
    }
  }

  /**
   * 创建分支们
   */
  createBranches() {
    let branches = [];
    let x = this.center.x;
    let y = this.center.y;
    //提前声明第一个分支的点数组
    let v = null;
    let d = this.d;
    //随机制造不同的分支，参数是实验的结果
    if (random()<0.5) {
      v = [new Vertex({x:x-0.05*this.d, y:y-0.15*this.d,snow:this}), new Vertex({x:x-0.05*d, y:y-0.25*d,snow:this}), new Vertex({x:x-0.2*d, y:y-0.35*d,snow:this}), 
        new Vertex({x:x-0.05*d, y:y-0.4*d,snow:this}), new Vertex({x:x, y:y-0.5*d,snow:this}), new Vertex({x:x+0.05*d,y:y-0.4*d,snow:this}), 
        new Vertex({x:x+0.2*d,y:y-0.35*d,snow:this}), new Vertex({x:x+0.05*d,y:y-0.25*d,snow:this}), new Vertex({x:x+0.05*d,y:y-0.15*d,snow:this})];
    } else {
      v = [new Vertex({x:x-0.2*d,y:y-0.15*d,snow:this}), new Vertex({x:x, y:y-0.5*d,snow:this}), new Vertex({x:x+0.2*d,y:y-0.15*d,snow:this})];
    }

    //构造第一个分支（上方分支）
    branches[0] = new Branch({v:v, r:255, g:240, b:240,snow:this});
    //利用旋转构造其余5个分支，色彩各不相同
    for (let i=1; i<6; i++) {
      branches[i] = new Branch({br:branches[i-1], angle:radians(60), r:220+4*i, g:255-7*i, b:200+11*i,snow:this}); //<>//
    }
    return branches;
  }

  /**
   * 分支上的点绕中心点旋转变换。
   * @param vertex是需要变换的点， angle为正是顺时针变换，为负则逆时针变换
   */
  vertexRotate(vertex, angle) {
    let dx = vertex.x - this.center.x;// x 差
    let dy = vertex.y - this.center.y;// y 差
    let newX = this.center.x + dx*cos(angle) - dy*sin(angle);
    let newY = this.center.y + dx*sin(angle) + dy*cos(angle);
    vertex.x = newX;
    vertex.y = newY;
  }
}

//内部类，记录雪花的各个重要点
class Vertex {

  /**
   * 指定坐标构造点
   */
  constructor(obj) {
    this.snow = obj.snow;
    if (obj.x) {
      this.x = obj.x;
      this.y = obj.y;
    } else {
      this.x = obj.v.x;
      this.y = obj.v.y;
      this.snow.vertexRotate(this, obj.angle);
    }
  }

  /**
   * 更新点的坐标
   */
  update() {
    //下落
    this.fall();
    //围绕雪花中心旋转
    this.snow.vertexRotate(this, this.snow.angularSpeed);
  }

  /**
   * 利用Branch里的beginShape()和endShape()，配合绘制分支
   */
  redraw() {
    vertex(this.x, this.y);
  }

  fall() {
    this.y += this.snow.fallSpeed;
  }
}

class Branch {
  constructor(obj) {
    this.snow = obj.snow;
    if (obj.v) {
      this.vertexs = obj.v;
    } else {
      this.vertexs = [];
      for (let i=0; i<obj.br.vertexs.length; i++) {
        this.vertexs[i] = new Vertex({ v:obj.br.vertexs[i], angle:obj.angle, snow:this.snow});
      }
    }
    this.r = obj.r;
    this.g = obj.g;
    this.b = obj.b;
  }


  /**
   * 更新这个分支
   */
  update() {
    for (let i in this.vertexs) {
      this.vertexs[i].update();
    }
  }

  /**
   * 绘制这个分支
   */
  redraw() {
    //修改填充颜色
    fill(this.r, this.g, this.b, this.snow.alpha);
    //分支是不规则形状
    beginShape();
    for (let i in this.vertexs) {
      this.vertexs[i].redraw();
    }
    endShape(CLOSE);
  }
}
