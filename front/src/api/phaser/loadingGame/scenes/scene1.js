import { Scene } from 'phaser';

export default class scene1 extends Scene {
  constructor() {
    super({ key: 'scene1' });
  }

  gameState = {};

  create() {
    // this.gameState.notice = this.add.text(40, 100, '최고 점수 : ', {
    //   font: '24px Arial',
    //   fill: '#000000',
    //   align: 'center',
    // });
    // this.gameState.notice = this.add.text(40, 132, '현재 점수 : ', {
    //   font: '24px Arial',
    //   fill: '#000000',
    //   align: 'center',
    // });

    this.gameState.chaltteogi = this.physics.add.sprite(0, 0, 'chaltteogi');
    this.gameState.chaltteogi.setScale(0.5);
    this.gameState.chaltteogi.setInteractive();
    this.gameState.chaltteogi.setCollideWorldBounds(true);
    this.gameState.chaltteogi.body.onWorldBounds = true; // enable worldbounds collision event
    this.gameState.chaltteogi.setBounce(1);
    this.gameState.chaltteogi.setVelocity(200, 20);
    this.gameState.chaltteogi.on('pointerdown', function () {
      this.setVelocityY(-500);
      console.log('되라고');
    });

    this.physics.world.checkCollision.down = true;

    this.physics.world.on(
      'worldbounds',
      function (blockedDown) {
        if (blockedDown == true) {
          alert('Game over!');
          location.reload();
        }
      },
      this,
    );

    this.physics.world.on(
      'worldbounds',
      function (blockedDown) {
        if (blockedDown == true) {
          alert('Game over!');
          location.reload();
        }
      },
      this,
    );
  }

  update() {
    this.gameState.chaltteogi.angle += 1;
  }
}
