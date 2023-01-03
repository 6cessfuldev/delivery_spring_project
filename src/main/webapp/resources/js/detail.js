const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: false,
  
    // // If we need pagination
    // pagination: {
    //   el: '.swiper-pagination',
    // },
  
    // Navigation arrows
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  
    // And if we need scrollbar
    scrollbar: {
      el: '.swiper-scrollbar',
    },
    width : 150
});

// var gallery = new SimpleLightbox('.gallery a',{
//   overlay:true
// });

// gallery.open();

(function() {
  if (Galleria) { $("body").text('Galleria works') }
}());

(function() {
  Galleria.loadTheme('galleria/themes/classic/galleria.classic.min.js');
  Galleria.run('.galleria');
}());